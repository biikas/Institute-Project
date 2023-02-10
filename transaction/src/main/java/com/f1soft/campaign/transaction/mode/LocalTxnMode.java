package com.f1soft.campaign.transaction.mode;


import com.f1soft.campaign.transaction.dto.TxnParameter;
import com.f1soft.campaign.transaction.dto.response.TxnResponse;
import com.f1soft.campaign.transaction.qualifier.LocalTransaction;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@LocalTransaction
public class LocalTxnMode implements TxnMode {

    @Override
    public TxnResponse doTxn(TxnParameter txnParameter) {
        log.info("Local transaction mode initiated");
        TxnResponse txnResponse = new TxnResponse();

        txnResponse.setTransferred(true);
        txnResponse.setCommissionPayee("");
        txnResponse.setCommissionAmount(0.0);
        txnResponse.setRequestInfoId(1L);
        txnResponse.setTransferedAmount(txnParameter.getAmount());
        txnResponse.setErrorMSG("Fund Transfer Success");
//        txnResponse.setTxnRefId(txnParameter.getRequestInfo().getId());
        return txnResponse;
    }

}
