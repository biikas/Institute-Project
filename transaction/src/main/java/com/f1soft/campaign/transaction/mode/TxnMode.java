package com.f1soft.campaign.transaction.mode;


import com.f1soft.campaign.transaction.dto.TxnParameter;
import com.f1soft.campaign.transaction.dto.response.TxnResponse;

public interface TxnMode {

    TxnResponse doTxn(TxnParameter txnParameter);

}
