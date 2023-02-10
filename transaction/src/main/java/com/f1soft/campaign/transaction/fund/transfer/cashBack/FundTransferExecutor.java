package com.f1soft.campaign.transaction.fund.transfer.cashBack;

import com.f1soft.campaign.common.dto.ServerResponse;
import com.f1soft.campaign.transaction.connector.transaction.TransactionRequestContext;
import com.f1soft.campaign.transaction.constant.BankconnectConstant;
import com.f1soft.campaign.transaction.dto.FundTransferInfo;
import com.f1soft.campaign.transaction.dto.IsoField;
import com.f1soft.campaign.transaction.dto.Partner;
import com.f1soft.campaign.transaction.dto.request.FundReversalRequest;
import com.f1soft.campaign.transaction.dto.request.FundTransferParamRequest;
import com.f1soft.campaign.transaction.dto.response.CbsFundReversalResponse;
import com.f1soft.campaign.transaction.dto.response.CbsFundTransferResponse;
import com.f1soft.campaign.transaction.dto.response.FundReversalResponse;
import com.f1soft.campaign.transaction.dto.response.FundTransferResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
public class FundTransferExecutor {

    @Autowired
    private TransactionRequestContext transactionRequestContext;

    public FundTransferResponse fundTransfer(FundTransferInfo fundTransferInfo) {
        FundTransferResponse fundTransferResponse = new FundTransferResponse();
        try {
            Long transactionRequestId = fundTransferInfo.getTxnRequestId();
            boolean doReversal = fundTransferInfo.isDoReversal();

            FundTransferParamRequest param = new FundTransferParamRequest();
            param.setFromAccount(fundTransferInfo.getFromAccount().trim());
            param.setToAccount(fundTransferInfo.getToAccount().trim());
            param.setAmount(fundTransferInfo.getAmount());
            param.setInitiator(fundTransferInfo.getInitiator());
            param.setTransactionId("CMP-" + fundTransferInfo.getTxnRequestId());

            param.setFields(fundTransferInfo.getIsoParam()
                    .entrySet()
                    .stream()
                    .map(field -> {
                        return new IsoField(field.getKey(), field.getValue());
                    }).collect(Collectors.toList())
            );

            HashMap<String, Double> partners = fundTransferInfo.getPartnersAmount();

            if (partners != null) {
                List<Partner> partnerList = new ArrayList<>();
                Iterator iterator = partners.keySet().iterator();
                while (iterator.hasNext()) {
                    Partner partner = new Partner();
                    String key = (String) iterator.next();
                    Double value = partners.get(key);

                    partner.setCommissionCode(key);
                    partner.setAccountNo(key);
                    partner.setAmount(value);
                    partnerList.add(partner);
                }
                param.setPartners(partnerList);
            }

            param.setCurrencyCode("NPR");
            param.setRequestId("CMP-" + transactionRequestId);

            param.setBranch(fundTransferInfo.getBranch());
            param.setServiceCode(fundTransferInfo.getServiceCode());

            ServerResponse serverResponse = transactionRequestContext.fundTransfer(param);

            if (serverResponse.getCode().equals(BankconnectConstant.SUCCESS)) {
                CbsFundTransferResponse cbsFundTransferResponse = (CbsFundTransferResponse) serverResponse.getObj();

                fundTransferResponse.setTransferred(true);
                fundTransferResponse.setStanId(cbsFundTransferResponse.getStanId());
                fundTransferResponse.setRequestTraceId(cbsFundTransferResponse.getRequestId());

                fundTransferResponse.setErrorCode("0");
                fundTransferResponse.setMessage("Fund Transfer Success");
            } else {
                processForReversal(String.valueOf(transactionRequestId), doReversal, "");
                fundTransferResponse.setTransferred(false);
                fundTransferResponse.setErrorCode(serverResponse.getCode());
                fundTransferResponse.setMessage(serverResponse.getMessage());
            }

        } catch (Exception e) {
            log.error("Exception : ", e);
            fundTransferResponse.setTransferred(false);
            fundTransferResponse.setErrorCode("-1");
            fundTransferResponse.setMessage("System Problem");
        }
        return fundTransferResponse;
    }

    public FundReversalResponse processForReversal(String transactionRequestId, boolean doReversal, String remarks) {
        if (doReversal) {
            FundReversalRequest fundReversalRequest = new FundReversalRequest();
            fundReversalRequest.setRequestId("CMP-" + transactionRequestId);
            fundReversalRequest.setRemarks(remarks);

            ServerResponse serverResponse = transactionRequestContext.reversal(fundReversalRequest);

            FundReversalResponse fundReversalResponse = new FundReversalResponse();
            if (serverResponse.getCode().equals(BankconnectConstant.SUCCESS)) {
                CbsFundReversalResponse cbsFundReversalResponse = (CbsFundReversalResponse) serverResponse.getObj();
                fundReversalResponse.setReversed(true);
                fundReversalResponse.setCode("0");
                fundReversalResponse.setMessage(serverResponse.getMessage());
                fundReversalResponse.setTxnRequestId(cbsFundReversalResponse.getRequestId());
                fundReversalResponse.setRespCode(cbsFundReversalResponse.getRespCode());
                fundReversalResponse.setRespDesc(cbsFundReversalResponse.getRespDesc());
            } else {
                fundReversalResponse.setReversed(false);
                fundReversalResponse.setCode("-1");
                fundReversalResponse.setMessage("Failed to reverse transaction");
            }
            return fundReversalResponse;
        }
        return null;
    }
}
