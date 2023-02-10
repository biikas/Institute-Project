package com.f1soft.campaign.transaction.connector.transaction.dataPackage;

import com.f1soft.campaign.common.dto.RequestWrapper;
import com.f1soft.campaign.common.dto.ServerResponse;
import com.f1soft.campaign.transaction.connector.CounterMerchantPaymentConnector;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;

/**
 * @author Bikash Shah
 */
@Slf4j
@Component
@Qualifier("live")
public class LiveDataPackageOperations implements DataPackageOperations {

    @Autowired
    private CounterMerchantPaymentConnector dataPackConnector;

    @Override
    public ServerResponse dataPackagePayment(RequestWrapper requestWrapper) {
        return dataPackConnector.request(requestWrapper, new ParameterizedTypeReference<RequestWrapper>() {
        });
    }
}
