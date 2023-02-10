package com.f1soft.campaign.web.notification.connector;

import com.f1soft.campaign.common.config.application.SystemConfig;
import com.f1soft.campaign.common.constant.AppConfigConstant;
import com.f1soft.campaign.common.dto.RestTemplateResponse;
import com.f1soft.campaign.common.dto.ServerResponse;
import com.f1soft.campaign.common.hmac.HmacBuilder;
import com.f1soft.campaign.common.http.RestNxTemplate;
import com.f1soft.campaign.web.notification.dto.HttpResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import static com.f1soft.campaign.common.util.RestHelper.buildHmacEntity;


@Slf4j
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
@Component
public class UserDetailConnector extends RestNxTemplate {

    @Autowired
    private SystemConfig systemConfig;

    public <T> ServerResponse request(String path, T requestData, ParameterizedTypeReference typeReference) {

        String baseUrl = systemConfig.appConfig(AppConfigConstant.BANKXP_SERVER_URL);
        String hmacKey = systemConfig.appConfig(AppConfigConstant.BANKXP_HMAC_KEY);
        String hmacSecret = systemConfig.appConfig(AppConfigConstant.BANKXP_HMAC_SECRET);

        log.debug("Base URL : {}", baseUrl);
        ServerResponse serverResponse = new ServerResponse();

        String url = baseUrl + path;

        HttpHeaders headers = new HttpHeaders();

        headers.add(HttpHeaders.AUTHORIZATION,
                new HmacBuilder().getHeader(
                        url,
                        requestData,
                        hmacKey,
                        hmacSecret));

        setRestTemplate(5, 30);
        RestTemplateResponse restTemplateResponse = doPost(
                url,
                buildHmacEntity(requestData, headers),
                typeReference);


        if (restTemplateResponse.isObtained()) {
            HttpResponse httpResponse = (HttpResponse) restTemplateResponse.getObj();
            if (httpResponse.getCode().equalsIgnoreCase("0")) {
                serverResponse.setSuccess(true);
                serverResponse.setCode("0");
                serverResponse.setObj(httpResponse.getData());
            } else {
                serverResponse.setCode(httpResponse.getCode());
                serverResponse.setMessage(httpResponse.getMessage());
            }
        } else {
            //exception case
            serverResponse.setCode(restTemplateResponse.getErrorCode());
            serverResponse.setMessage(restTemplateResponse.getErrorMessage());
        }
        return serverResponse;
    }
}
