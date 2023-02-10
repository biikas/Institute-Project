package com.f1soft.campaign.transaction.connector;

import com.f1soft.campaign.common.config.application.SystemConfig;
import com.f1soft.campaign.common.constant.AppConfigConstant;
import com.f1soft.campaign.common.dto.RestTemplateResponse;
import com.f1soft.campaign.common.dto.ServerResponse;
import com.f1soft.campaign.common.hmac.HmacBuilder;
import com.f1soft.campaign.common.http.RestNxTemplate;
import com.f1soft.campaign.common.util.RestHelper;
import com.f1soft.campaign.transaction.connector.BankConnectPath;
import com.f1soft.campaign.transaction.constant.BankconnectConstant;
import com.f1soft.campaign.transaction.dto.response.CbsResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;


@Slf4j
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
@Component
@SuppressWarnings("Duplicates")
public class CbsConnector extends RestNxTemplate {

    @Autowired
    private SystemConfig systemConfig;

    private String baseUrl;
    private String hmacKey;
    private String hmacSecret;

    public void init() {
        this.baseUrl = systemConfig.appConfig(AppConfigConstant.BANCSCONNECT_URL);
        this.hmacKey = systemConfig.appConfig(AppConfigConstant.BANCSCONNECT_HMAC_KEY);
        this.hmacSecret = systemConfig.appConfig(AppConfigConstant.BANCSCONNECT_HMAC_SECRET);
    }

    /**
     * Response code form bankconnect
     * code 0 = success and data fetched
     * code 1 = sucess and data not found
     * code -1 = Exception in bankconnect
     * <p>
     * <p>
     * Response code from bankcsconnect
     * code 0 = success and data fetched
     * code 1 = success and data nor found
     * code 3 = gateway exception
     * code -6 = internal server error
     * code -10 = read timeout
     * code -1 = connect timeout
     *
     * @param path
     * @param requestData
     * @param typeReference
     * @param <T>
     * @return
     */
    public <T> ServerResponse request(String path, T requestData, ParameterizedTypeReference typeReference) {
        init();
        ServerResponse serverResponse = new ServerResponse();

        String url = BankConnectPath.build(baseUrl, path);

        HttpHeaders headers = new HttpHeaders();

        headers.add(HttpHeaders.AUTHORIZATION,
                new HmacBuilder().getHeader(
                        url,
                        requestData,
                        hmacKey,
                        hmacSecret));

//        return cbsConnector.request(
//                BankConnectPath.Transaction.FUND_TRANSFER,
//                fundTransferParamRequest,
//                new ParameterizedTypeReference<CbsResponse<CbsFundTransferResponse>>() {
//                });

        setRestTemplate(5, 60);

        RestTemplateResponse restTemplateResponse = doPost(
                url,
                RestHelper.buildHmacEntity(requestData, headers)
                , typeReference);


        if (restTemplateResponse.isObtained()) {

            CbsResponse cbsResponse = (CbsResponse) restTemplateResponse.getObj();
            log.debug("cbs response : {}", cbsResponse);

            if (cbsResponse.getCode().equalsIgnoreCase(BankconnectConstant.SUCCESS)) {
                serverResponse.setObj(cbsResponse.getData());
                serverResponse.setSuccess(true);
                serverResponse.setCode("0");
                serverResponse.setMessage(cbsResponse.getMessage());
            } else if (cbsResponse.getCode().equalsIgnoreCase(BankconnectConstant.DATA_NOT_FOUND)) {
                serverResponse.setSuccess(true);
                serverResponse.setCode(cbsResponse.getCode());
                serverResponse.setMessage(cbsResponse.getMessage());
            } else if (cbsResponse.getCode().equalsIgnoreCase(BankconnectConstant.TIMEOUT)) {
                serverResponse.setSuccess(false);
                serverResponse.setCode(cbsResponse.getCode());
                serverResponse.setMessage(cbsResponse.getMessage());
            } else {
                serverResponse.setSuccess(false);
                serverResponse.setCode("2");
                serverResponse.setMessage(cbsResponse.getMessage());
            }

        } else {
            //exception case
            serverResponse.setSuccess(false);
            serverResponse.setCode(restTemplateResponse.getErrorCode());
            serverResponse.setMessage(restTemplateResponse.getErrorMessage());
        }
        return serverResponse;
    }
}

