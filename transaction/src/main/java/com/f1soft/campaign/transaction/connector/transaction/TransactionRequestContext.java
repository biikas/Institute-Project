package com.f1soft.campaign.transaction.connector.transaction;

import com.f1soft.campaign.common.constant.MsgConstant;
import com.f1soft.campaign.common.dto.RequestWrapper;
import com.f1soft.campaign.common.dto.ServerResponse;
import com.f1soft.campaign.common.exception.ServerException;
import com.f1soft.campaign.common.util.ResponseMsg;
import com.f1soft.campaign.transaction.dto.request.FundReversalRequest;
import com.f1soft.campaign.transaction.dto.request.FundTransferParamRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@Slf4j
@Component
public class TransactionRequestContext {

    @Autowired
    private TransactionRequestFactory transactionRequestFactory;

    public ServerResponse fundTransfer(FundTransferParamRequest fundTransferParamRequest) {
        return transactionRequestFactory.getTransactionOperations().fundTransfer(fundTransferParamRequest);
    }

    public ServerResponse reversal(FundReversalRequest fundReversalRequest) {
        return transactionRequestFactory.getTransactionOperations().reversal(fundReversalRequest);
    }

    private ServerResponse getFutureResponse(Future<ServerResponse> future) {
        try {
            return future.get();
        } catch (InterruptedException | ExecutionException e) {
            throw new ServerException(ResponseMsg.failureResponse(MsgConstant.INTERNAL_SERVER_ERROR));
        }
    }
}
