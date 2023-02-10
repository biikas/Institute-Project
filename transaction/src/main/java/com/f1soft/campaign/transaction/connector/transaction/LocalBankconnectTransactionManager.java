package com.f1soft.campaign.transaction.connector.transaction;

import com.f1soft.campaign.common.dto.RequestWrapper;
import com.f1soft.campaign.common.dto.ServerResponse;
import com.f1soft.campaign.common.util.RandomGenerator;
import com.f1soft.campaign.transaction.dto.response.CbsFundReversalResponse;
import com.f1soft.campaign.transaction.dto.response.CbsFundTransferResponse;
import com.f1soft.campaign.transaction.dto.request.FundReversalRequest;
import com.f1soft.campaign.transaction.dto.request.FundTransferParamRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Slf4j
@SuppressWarnings({"Duplicates"})
@Component
@Qualifier("local")
public class LocalBankconnectTransactionManager implements BankconnectTransactionOperations {

    @Override
    public ServerResponse fundTransfer(FundTransferParamRequest fundTransferParamRequest) {

        ServerResponse serverResponse = new ServerResponse();

        CbsFundTransferResponse cbsFundTransferResponse = new CbsFundTransferResponse();

        cbsFundTransferResponse.setRespCode("0");
        cbsFundTransferResponse.setRespDesc("Success");
        cbsFundTransferResponse.setBenificiaryName("BAL KRISHNA SAMA");
        cbsFundTransferResponse.setStanId(String.valueOf(RandomGenerator.generate(6)));
        cbsFundTransferResponse.setRequestId(String.valueOf(RandomGenerator.generate(6)));

        serverResponse.setSuccess(true);
        serverResponse.setCode("0");
        serverResponse.setMessage("Fund transfer success");
        serverResponse.setObj(cbsFundTransferResponse);
        return serverResponse;
    }

    @Override
    public ServerResponse reversal(FundReversalRequest fundReversalRequest) {
        ServerResponse serverResponse = new ServerResponse();
        CbsFundReversalResponse cbsFundReversalResponse = new CbsFundReversalResponse();
        cbsFundReversalResponse.setRequestId(fundReversalRequest.getRequestId());
        cbsFundReversalResponse.setRespCode("0");
        cbsFundReversalResponse.setRespDesc("Success");

        serverResponse.setSuccess(true);
        serverResponse.setCode("0");
        serverResponse.setMessage("Fund reversal success");
        serverResponse.setObj(cbsFundReversalResponse);
        return serverResponse;

    }

}
