package com.f1soft.campaign.transaction.fund.transfer.accountLoad;

import com.f1soft.campaign.common.constant.MsgConstant;
import com.f1soft.campaign.common.constant.Parameter;
import com.f1soft.campaign.common.dto.ServerResponse;
import com.f1soft.campaign.common.enums.OfferTransactionStatusEnum;
import com.f1soft.campaign.common.helper.CampaignTotalRedeemUpdater;
import com.f1soft.campaign.common.util.MessageComposer;
import com.f1soft.campaign.common.util.ResponseMsg;
import com.f1soft.campaign.entities.model.OfferTransaction;
import com.f1soft.campaign.entities.model.OfferTxnLog;
import com.f1soft.campaign.entities.model.RequestInfo;
import com.f1soft.campaign.repository.OfferTransactionRepository;
import com.f1soft.campaign.repository.OfferTxnLogRepository;
import com.f1soft.campaign.transaction.constant.BankconnectConstant;
import com.f1soft.campaign.transaction.dto.TxnParameter;
import com.f1soft.campaign.transaction.dto.response.TxnResponse;
import com.f1soft.campaign.transaction.fund.transfer.Redeem;
import com.f1soft.campaign.transaction.mapper.OfferTxnLogMapper;
import com.f1soft.campaign.transaction.mode.TxnMode;
import com.f1soft.campaign.transaction.qualifier.BankTransaction;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author Shreetika Panta
 */

@Slf4j
@Component
@Qualifier("accountLoad")
public class AccountLoad extends Redeem {

    @Autowired
    private AccountLoadHandler transferHandler;
    @Autowired
    @BankTransaction
    private TxnMode txnMode;
    @Autowired
    private OfferTransactionRepository offerTransactionRepository;
    @Autowired
    private OfferTxnLogRepository offerTxnLogRepository;
    @Autowired
    private CampaignTotalRedeemUpdater campaignTotalRedeemUpdater;

    @Override
    protected ServerResponse pay(RequestInfo requestInfo) {
        ServerResponse serverResponse = new ServerResponse();
        log.debug("Initiating Fund Transfer.");

        TxnParameter txnParameter = transferHandler.buildParameters(fundTransferRequestData, requestInfo);

        TxnResponse txnResponse = txnMode.doTxn(txnParameter);

        serverResponse.setObj(txnResponse);

        updateTransaction(txnResponse);
        if (txnResponse.isTransferred()) {
            campaignTotalRedeemUpdater.updateCampaignTotalRedeem(requestInfo.getCampaign(), fundTransferRequestData.getAmount());

            return ResponseMsg.successResponse(MsgConstant.FundTransfer.REFUND_SUCCESS,
                    MessageComposer.getParameters(Parameter.AMOUNT, String.valueOf(fundTransferRequestData.getAmount()),
                            Parameter.MOBILE_NUMBER, String.valueOf(fundTransferRequestData.getOfferTransaction().getMobileNumber()),
                            Parameter.ACCOUNT_NUMBER, fundTransferRequestData.getOfferTransaction().getAccountNumber()));
        } else {
            return ResponseMsg.failureResponse(MsgConstant.FundTransfer.REFUND_FAILURE);
        }
    }

    protected void updateTransaction(TxnResponse txnResponse) {
        OfferTransaction offerTransaction = fundTransferRequestData.getOfferTransaction();
        offerTransaction.setTransactionDate(new Date());
        offerTransaction.setStatus("COMPLETED");

        OfferTxnLog offerTxnLog = OfferTxnLogMapper.convertToOfferTxnLog(offerTransaction);
        if (txnResponse.isTransferred()) {
            offerTransaction.setTransactionStatus(OfferTransactionStatusEnum.SUCCESS.name());
            if (fundTransferRequestData.getRemarks() != null) {
                offerTransaction.setRemarks(fundTransferRequestData.getRemarks());
            } else {
                offerTransaction.setRemarks(OfferTransactionStatusEnum.SUCCESS.name());
            }
            offerTxnLog.setStatus(OfferTransactionStatusEnum.SUCCESS.name());
            offerTxnLog.setRemarks(OfferTransactionStatusEnum.SUCCESS.name());
        } else {
            offerTxnLog.setStatus(OfferTransactionStatusEnum.FAILED.name());
            offerTxnLog.setRemarks(txnResponse.getErrorMSG());

            offerTransaction.setRemarks(txnResponse.getErrorMSG());

            if (txnResponse.getErrorCode().equalsIgnoreCase(BankconnectConstant.TIMEOUT)) {
                offerTransaction.setTransactionStatus(OfferTransactionStatusEnum.TIMEOUT.name());
            } else if (txnResponse.getErrorCode().equalsIgnoreCase(BankconnectConstant.FAILURE)) {
                offerTransaction.setTransactionStatus(OfferTransactionStatusEnum.FAILED.name());
            } else {
                offerTransaction.setTransactionStatus(OfferTransactionStatusEnum.AMBIGUOUS.name());
            }
        }
        offerTransactionRepository.save(offerTransaction);
        offerTxnLogRepository.save(offerTxnLog);
    }

}
