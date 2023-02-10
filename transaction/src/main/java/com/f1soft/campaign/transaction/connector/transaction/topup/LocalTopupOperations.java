package com.f1soft.campaign.transaction.connector.transaction.topup;

import com.f1soft.campaign.common.dto.RequestWrapper;
import com.f1soft.campaign.common.dto.ServerResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * @author Bikash Shah
 */
@Slf4j
@Component
@Qualifier("local")
public class LocalTopupOperations implements TopupOperations{
    @Override
    public ServerResponse topupPayment(RequestWrapper requestWrapper) {
        ServerResponse serverResponse = new ServerResponse();
        serverResponse.setSuccess(true);
        serverResponse.setCode("0");
        return serverResponse;
    }
}
