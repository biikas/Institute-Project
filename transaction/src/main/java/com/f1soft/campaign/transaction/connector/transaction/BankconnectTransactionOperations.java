package com.f1soft.campaign.transaction.connector.transaction;


import com.f1soft.campaign.common.dto.RequestWrapper;
import com.f1soft.campaign.common.dto.ServerResponse;
import com.f1soft.campaign.transaction.dto.request.FundReversalRequest;
import com.f1soft.campaign.transaction.dto.request.FundTransferParamRequest;

import java.util.concurrent.Future;

public interface BankconnectTransactionOperations {

    ServerResponse fundTransfer(FundTransferParamRequest fundTransferParamRequest);

    ServerResponse reversal(FundReversalRequest fundReversalRequest);

}
