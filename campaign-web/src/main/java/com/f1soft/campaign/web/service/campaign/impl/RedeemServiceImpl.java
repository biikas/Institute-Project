package com.f1soft.campaign.web.service.campaign.impl;

import com.f1soft.campaign.common.config.application.SystemConfig;
import com.f1soft.campaign.common.config.constant.AdminConfigConstant;
import com.f1soft.campaign.common.constant.MsgConstant;
import com.f1soft.campaign.common.dto.ServerResponse;
import com.f1soft.campaign.common.enums.OfferTransactionStatusEnum;
import com.f1soft.campaign.common.exception.InvalidDataException;
import com.f1soft.campaign.common.helper.IdHelper;
import com.f1soft.campaign.common.util.ResponseMsg;
import com.f1soft.campaign.entities.model.ApplicationUser;
import com.f1soft.campaign.entities.model.OfferTransaction;
import com.f1soft.campaign.entities.model.OfferTxnLog;
import com.f1soft.campaign.repository.ApplicationUserRepository;
import com.f1soft.campaign.repository.OfferTransactionRepository;
import com.f1soft.campaign.repository.OfferTxnLogRepository;
import com.f1soft.campaign.transaction.dto.RedeemRequestData;
import com.f1soft.campaign.transaction.fund.transfer.Redeem;
import com.f1soft.campaign.transaction.fund.transfer.RedeemFactory;
import com.f1soft.campaign.web.config.provider.LoginProvider;
import com.f1soft.campaign.web.dto.RefundDTO;
import com.f1soft.campaign.web.dto.request.ManualRedeemRequest;
import com.f1soft.campaign.web.dto.request.RedeemRequest;
import com.f1soft.campaign.web.dto.response.RedeemResponse;
import com.f1soft.campaign.web.service.campaign.RefundService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Prajwol Hada
 */
@Slf4j
@Service
public class RedeemServiceImpl implements RefundService {

    @Autowired
    private SystemConfig systemConfig;
    @Autowired
    private RedeemFactory redeemFactory;
    @Autowired
    private ApplicationUserRepository applicationUserRepository;
    @Autowired
    private OfferTransactionRepository offerTransactionRepository;
    @Autowired
    private OfferTxnLogRepository offerTxnLogRepository;

    @Override
    public ServerResponse redeem(RedeemRequest refundRequest) {

        int successCount = 0;
        int failureCount = 0;
        List<RefundDTO> refundDTOList = new ArrayList<>();

        ApplicationUser applicationUser;
        if (LoginProvider.findApplicationUser().isPresent()) {
            applicationUser = LoginProvider.getApplicationUser();
        } else {
            applicationUser = applicationUserRepository.findApplicationUserByUsername(systemConfig.adminConfig(AdminConfigConstant.DEFAULT_ADMIN_USERNAME)).get();
        }

        for (Long offerTransactionId : refundRequest.getOfferTransactions()) {
            Optional<OfferTransaction> offerTransactionOptional = offerTransactionRepository.findById(offerTransactionId);
            if (!offerTransactionOptional.isPresent()) {
                throw new InvalidDataException(MsgConstant.INVALID_DATA);
            }

            OfferTransaction offerTransaction = offerTransactionOptional.get();

            String fromAccount = offerTransaction.getCampaign().getOfferAccount();

            RedeemRequestData redeemRequestData = RedeemRequestData.builder()
                    .applicationUser(applicationUser)
                    .campaign(offerTransaction.getCampaign())
                    .offerTransaction(offerTransaction)
                    .toAccount(offerTransaction.getAccountNumber())
                    .amount(offerTransaction.getAmount())
                    .accountNumber(fromAccount)
                    .remarks(refundRequest.getRemarks())
                    .build();

            Redeem redeem = redeemFactory.getRedeemService(redeemRequestData.getOfferTransaction());

            ServerResponse paymentResponse = redeem.doPayment(redeemRequestData);

            RefundDTO refundDTO;
            if (paymentResponse.isSuccess()) {
                refundDTO = new RefundDTO(offerTransactionId, true);
                successCount++;
            } else {
                refundDTO = new RefundDTO(offerTransactionId, false);
                failureCount++;
            }
            refundDTOList.add(refundDTO);
        }
        RedeemResponse redeemResponse = new RedeemResponse(refundDTOList, successCount, failureCount);
        return ResponseMsg.successResponse(MsgConstant.Data.SUCCESS, redeemResponse);
    }

    @Override
    public ServerResponse acceptManualRedeem(ManualRedeemRequest manualRedeemRequest) {
        RedeemRequest redeemRequest = getRedeemRequest(manualRedeemRequest);

        ServerResponse serverResponse = redeem(redeemRequest);

        return serverResponse;
    }

    @Override
    public ServerResponse failRedeem(ManualRedeemRequest manualRedeemRequest) {
        RedeemRequest redeemRequest = getRedeemRequest(manualRedeemRequest);

        for (Long offerTransactionId : redeemRequest.getOfferTransactions()) {
            Optional<OfferTransaction> offerTransactionOptional = offerTransactionRepository.findById(offerTransactionId);
            if (!offerTransactionOptional.isPresent()) {
                throw new InvalidDataException(MsgConstant.INVALID_DATA);
            }

            OfferTransaction offerTransaction = offerTransactionOptional.get();
            offerTransaction.setStatus(OfferTransactionStatusEnum.REJECTED.name());
            offerTransaction.setRemarks(redeemRequest.getRemarks());
            offerTransaction.setTransactionStatus(OfferTransactionStatusEnum.REJECTED.name());

            OfferTxnLog offerTxnLog = new OfferTxnLog();
            offerTxnLog.setOfferTransaction(offerTransaction);
            offerTxnLog.setRemarks(redeemRequest.getRemarks());
            offerTxnLog.setStatus(OfferTransactionStatusEnum.REJECTED.name());
            offerTxnLog.setAddedDate(new Date());
            offerTransaction.setTransactionStatus(OfferTransactionStatusEnum.REJECTED.name());

            offerTransactionRepository.save(offerTransaction);
            offerTxnLogRepository.save(offerTxnLog);
        }


        return ResponseMsg.successResponse(MsgConstant.Data.SUCCESS);
    }

    public RedeemRequest getRedeemRequest(ManualRedeemRequest manualRedeemRequest) {
        RedeemRequest redeemRequest = new RedeemRequest();

        List<Long> offerTransactionsIds = manualRedeemRequest.getOfferTransactions().stream()
                .map(offerTransactionId -> IdHelper.getLongValue(offerTransactionId))
                .collect(Collectors.toList());

        redeemRequest.setOfferTransactions(offerTransactionsIds);
        redeemRequest.setRemarks(manualRedeemRequest.getRemarks());

        return redeemRequest;
    }

    public ServerResponse redeemAll() {

        List<OfferTransaction> offerTransactionList = offerTransactionRepository.getAllManualOfferTransactions();
        List<Long> offerTransactionsIds = offerTransactionList.stream()
                .map(offerTransaction -> offerTransaction.getId())
                .collect(Collectors.toList());

        RedeemRequest redeemRequest = new RedeemRequest();
        redeemRequest.setOfferTransactions(offerTransactionsIds);

        ServerResponse serverResponse = redeem(redeemRequest);

        return serverResponse;
    }
}
