package com.f1soft.campaign.transaction.mode;

import com.f1soft.campaign.entities.model.IsoTxnRequest;
import com.f1soft.campaign.entities.model.RequestInfo;
import com.f1soft.campaign.repository.IsoTxnRequestRepository;
import com.f1soft.campaign.transaction.fund.transfer.cashBack.Transaction;
import com.f1soft.campaign.transaction.constant.FundTransferConstant;
import com.f1soft.campaign.transaction.constant.TxnConstant;
import com.f1soft.campaign.transaction.dto.FundTransferInfo;
import com.f1soft.campaign.transaction.dto.TxnParameter;
import com.f1soft.campaign.transaction.dto.response.FundTransferResponse;
import com.f1soft.campaign.transaction.dto.response.TxnResponse;
import com.f1soft.campaign.transaction.mapper.TxnMapper;
import com.f1soft.campaign.transaction.qualifier.BankTransaction;
import com.f1soft.campaign.transaction.util.AmountFormatter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Slf4j
@BankTransaction
@Component
public class AccountMode implements TxnMode {

//    @Autowired
//    private CommissionCalculationRepository commissionCalculationRepository;
//    @Autowired
//    private PartnerCommissionCalculationRepository partnerCommissionCalculationRepository;

    @Autowired
    private IsoTxnRequestRepository isoTxnRequestRepository;

    @Autowired
    private Transaction transaction;

    @Override
    public TxnResponse doTxn(TxnParameter txnParameter) {
        String fromAccount = txnParameter.getFromAccount();
        String toAccount = txnParameter.getToAccount();
        Double amount = txnParameter.getAmount();

        Map<String, String> isoMap = txnParameter.getIsoMap();

        String extraCharge = txnParameter.getExtraCharge();
        Double extraChargeAmount = txnParameter.getExtraChargeAmount();

        String userName = txnParameter.getInitiator();
        RequestInfo requestInfo = txnParameter.getRequestInfo();

        TxnResponse txnResponse = new TxnResponse();
        txnResponse.setRequestInfoId(requestInfo.getId());

        IsoTxnRequest isoTxnRequest = TxnMapper.convertToIsoTxnRequest(fromAccount, toAccount, amount, requestInfo);
        isoTxnRequestRepository.save(isoTxnRequest);

        if (isoTxnRequest != null) {
            FundTransferInfo fundTranInfo = new FundTransferInfo();
            fundTranInfo.setInitiator(userName);
            fundTranInfo.setFromAccount(fromAccount);
            fundTranInfo.setToAccount(toAccount);
            fundTranInfo.setAmount(amount);
            fundTranInfo.setIsoParam(isoMap);
            fundTranInfo.setTxnRequestId(isoTxnRequest.getId());
            fundTranInfo.setExtraChargeType(extraCharge);
            fundTranInfo.setExtraChargeAmount(extraChargeAmount);
            fundTranInfo.setBranch(txnParameter.getBranchInfoDTO());
            fundTranInfo.setServiceCode(txnParameter.getServiceCode());

            FundTransferResponse response = transaction.initiateTxn(fundTranInfo);

            isoTxnRequest.setTxnErrorCode(response.getErrorCode());
            isoTxnRequest.setTxnErrorDesc(response.getMessage());
            isoTxnRequest.setTxnAmount(AmountFormatter.doubleToBigDecimal(response.getTxnAmount()));
            isoTxnRequest.setTxnTraceId(response.getRequestTraceId());
            isoTxnRequest.setIsoResponseId(response.getStanId());

            isoTxnRequest.setUtilityPaymentStatus(TxnConstant.UTILITY_TO_PROCESS);

            log.debug("Fund transfer status : {}", response.isTransferred());
            if (response.isTransferred()) {
                isoTxnRequest.setTxnStatus(FundTransferConstant.FUND_TRANSFER_SUCCESS);
                txnResponse.setCommissionAmount(0.0);
            } else {
                isoTxnRequest.setTxnStatus(TxnConstant.FUND_TRANSFER_FAILURE);
            }
            isoTxnRequestRepository.save(isoTxnRequest);
            txnResponse.setTransferred(response.isTransferred());
            txnResponse.setTransferedAmount(response.getTxnAmount());
            txnResponse.setTxnRefId(isoTxnRequest.getId());
            txnResponse.setBenificiaryName(response.getBenificiaryName());

            txnResponse.setErrorCode(response.getErrorCode());
            txnResponse.setErrorMSG(response.getMessage());

        } else {
            txnResponse.setErrorCode("SYE");
            txnResponse.setErrorMSG("Could not insert in ISO Request");
        }
        return txnResponse;
    }
}
