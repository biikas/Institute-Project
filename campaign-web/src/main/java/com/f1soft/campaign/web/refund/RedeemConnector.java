package com.f1soft.campaign.web.refund;


import com.f1soft.campaign.common.config.application.SystemConfig;
import com.f1soft.campaign.common.constant.AppConfigConstant;
import com.f1soft.campaign.common.dto.RestTemplateResponse;
import com.f1soft.campaign.common.dto.ServerResponse;
import com.f1soft.campaign.common.hmac.HmacBuilder;
import com.f1soft.campaign.common.http.RestNxTemplate;
import com.f1soft.campaign.common.util.RestHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

@Slf4j
@SuppressWarnings({"Duplicates"})
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
@Component
public class RedeemConnector extends RestNxTemplate {

    @Autowired
    private SystemConfig systemConfig;

    private String baseUrl;
    private String hmacKey;
    private String hmacSecret;

    public void init() {
        this.baseUrl = systemConfig.appConfig(AppConfigConstant.CAMPAIGN_SERVER_URL);
        this.hmacKey = systemConfig.appConfig(AppConfigConstant.CAMPAIGN_HMAC_KEY);
        this.hmacSecret = systemConfig.appConfig(AppConfigConstant.CAMPAIGN_HMAC_SECRET);
    }

    public <T> ServerResponse request(String path, T requestData, ParameterizedTypeReference typeReference) {
        init();
        String baseUrl = systemConfig.appConfig(AppConfigConstant.CAMPAIGN_SERVER_URL);
        ServerResponse serverResponse = new ServerResponse();

        String url = baseUrl + path;
        log.debug("Socket notification url : {} with body : {}", url, requestData);

        HttpHeaders headers = new HttpHeaders();

        headers.add(HttpHeaders.AUTHORIZATION,
                new HmacBuilder().getHeader(
                        url,
                        requestData,
                        hmacKey,
                        hmacSecret));

        setRestTemplate(5, 60);

        RestTemplateResponse restTemplateResponse = doPost(
                url,
                RestHelper.buildHmacEntity(requestData, headers)
                , typeReference);


        if (restTemplateResponse.isObtained()) {
            log.info("Object : {}", restTemplateResponse.getObj());
            return (ServerResponse) restTemplateResponse.getObj();
        } else {
            //exception case
            serverResponse.setSuccess(false);
            serverResponse.setMessage("Failed to obtain response from server.");
        }

        return serverResponse;


    }
}
