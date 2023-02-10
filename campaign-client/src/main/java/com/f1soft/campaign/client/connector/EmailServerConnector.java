package com.f1soft.campaign.client.connector;

import com.f1soft.campaign.client.loader.DataConfigLoader;
import com.f1soft.campaign.common.config.application.SystemConfig;
import com.f1soft.campaign.common.config.constant.AppConfigConstant;
import com.f1soft.campaign.common.constant.HmacClientConstant;
import com.f1soft.campaign.common.dto.HttpJobResponse;
import com.f1soft.campaign.common.dto.RestTemplateResponse;
import com.f1soft.campaign.common.hmac.HmacBuilder;
import com.f1soft.campaign.common.http.RestNxTemplate;
import com.f1soft.campaign.entities.model.HmacClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import static com.f1soft.campaign.common.util.RestHelper.buildHmacEntity;

/**
 * @author Rashim Dhaubanjar
 */

@Slf4j
@SuppressWarnings({"Duplicates"})
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
@Component
public class EmailServerConnector extends RestNxTemplate {

    @Autowired
    private SystemConfig systemConfig;
    @Autowired
    private DataConfigLoader dataConfigLoader;

    private String baseUrl;
    private String hmacKey;
    private String hmacSecret;

    public void init() {
        this.baseUrl = systemConfig.appConfig(AppConfigConstant.EMAIL_SERVER_URL);
        HmacClient hmacClient = dataConfigLoader.hmacClientMapMap().get(HmacClientConstant.SYSTEM_HMAC_CLIENT);
        this.hmacKey = hmacClient.getApiKey();
        this.hmacSecret = hmacClient.getApiSecret();
    }

    public <T> HttpJobResponse request(String path, T requestData, ParameterizedTypeReference typeReference) {
        init();
        setRestTemplate(5, 20);

        HttpJobResponse httpJobResponse = new HttpJobResponse();

        String url = baseUrl.concat(path);
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.AUTHORIZATION,
                new HmacBuilder().getHeader(
                        url,
                        requestData,
                        hmacKey,
                        hmacSecret));

        RestTemplateResponse restTemplateResponse = doPost(
                url,
                buildHmacEntity(requestData, headers)
                , typeReference);


        if (restTemplateResponse.isObtained()) {
            return (HttpJobResponse) restTemplateResponse.getObj();
        } else {
            //exception case
            httpJobResponse.setSuccess(false);
            httpJobResponse.setMessage("Failed to obtain response from server.");
        }
        return httpJobResponse;
    }
}
