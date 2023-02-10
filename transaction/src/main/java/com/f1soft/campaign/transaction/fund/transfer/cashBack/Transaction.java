package com.f1soft.campaign.transaction.fund.transfer.cashBack;

import com.f1soft.campaign.transaction.dto.FundTransferInfo;
import com.f1soft.campaign.transaction.dto.response.FundTransferResponse;
import com.f1soft.campaign.transaction.fund.transfer.cashBack.FundTransferExecutor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Transaction {

    @Autowired
    private FundTransferExecutor fundTransferExecutor;

    public FundTransferResponse initiateTxn(FundTransferInfo fundTranInfo) {
        FundTransferResponse fundTransferResponse = new FundTransferResponse();
        try {
            Double amount = fundTranInfo.getAmount();
            fundTransferResponse = fundTransferExecutor.fundTransfer(fundTranInfo);

            fundTransferResponse.setTxnAmount(fundTranInfo.getAmount());
        } catch (Exception e) {
            log.error("Exception : ", e);
            fundTransferResponse.setTransferred(false);
            fundTransferResponse.setTxnAmount(fundTranInfo.getAmount());
            fundTransferResponse.setErrorCode("-1");
            fundTransferResponse.setMessage("System Problem");
        }
        return fundTransferResponse;
    }
}