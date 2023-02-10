package com.f1soft.campaign.transaction.connector.transaction;

import com.f1soft.campaign.common.dto.ServerResponse;
import com.f1soft.campaign.transaction.connector.BankConnectPath;
import com.f1soft.campaign.transaction.connector.CbsConnector;
import com.f1soft.campaign.transaction.connector.CounterMerchantPaymentConnector;
import com.f1soft.campaign.transaction.dto.request.FundReversalRequest;
import com.f1soft.campaign.transaction.dto.request.FundTransferParamRequest;
import com.f1soft.campaign.transaction.dto.response.CbsFundReversalResponse;
import com.f1soft.campaign.transaction.dto.response.CbsFundTransferResponse;
import com.f1soft.campaign.transaction.dto.response.CbsResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Qualifier("live")
public class BankconnectTransactionManager implements BankconnectTransactionOperations {

    @Autowired
    private CbsConnector cbsConnector;

    @Autowired
    private CounterMerchantPaymentConnector topupConnector;

    @Override
    public ServerResponse fundTransfer(FundTransferParamRequest fundTransferParamRequest) {

        return cbsConnector.request(
                BankConnectPath.Transaction.FUND_TRANSFER,
                fundTransferParamRequest,
                new ParameterizedTypeReference<CbsResponse<CbsFundTransferResponse>>() {
                });
    }

    @Override
    public ServerResponse reversal(FundReversalRequest fundReversalRequest) {
        return cbsConnector.request(
                BankConnectPath.Transaction.FUND_REVERSAL,
                fundReversalRequest,
                new ParameterizedTypeReference<CbsResponse<CbsFundReversalResponse>>() {
                });
    }
}