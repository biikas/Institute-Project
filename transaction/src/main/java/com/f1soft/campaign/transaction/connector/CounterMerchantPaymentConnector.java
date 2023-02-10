package com.f1soft.campaign.transaction.connector;

import com.f1soft.campaign.common.config.application.SystemConfig;
import com.f1soft.campaign.common.constant.AppConfigConstant;
import com.f1soft.campaign.common.dto.RestTemplateResponse;
import com.f1soft.campaign.common.dto.ServerResponse;
import com.f1soft.campaign.common.http.RestNxTemplate;
import com.f1soft.campaign.common.util.RestHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

/**
 * @author Bikash Shah
 */
@Slf4j
@Component
@RequestScope
public class CounterMerchantPaymentConnector extends RestNxTemplate {
    @Autowired
    private SystemConfig systemConfig;

    private static final String COUNTER_MERCHANT_PAYMENT_URL = "payments/counterMerchantPayment";

    public <T> ServerResponse request(T requestData, ParameterizedTypeReference typeReference) {
        String baseUrl = systemConfig.appConfig(AppConfigConstant.BANKXP_SERVER_URL);
        String url = baseUrl.concat(COUNTER_MERCHANT_PAYMENT_URL);

        log.info("Socket notification url : {} with body : {}", requestData);

        ServerResponse serverResponse = new ServerResponse();

        HttpHeaders headers = new HttpHeaders();

        RestTemplateResponse restTemplateResponse = doPost(url, RestHelper.buildHmacEntity(requestData, headers), typeReference);

        if (restTemplateResponse.isObtained()) {
            serverResponse.setSuccess(true);
            serverResponse.setObj(restTemplateResponse.getObj());
            serverResponse.setCode("0");
        } else {
            serverResponse.setSuccess(false);
            serverResponse.setCode(restTemplateResponse.getErrorCode());
            serverResponse.setMessage(restTemplateResponse.getErrorMessage());
        }
        return serverResponse;
    }
}
