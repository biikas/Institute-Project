package com.f1soft.campaign.common.http;

import com.f1soft.campaign.common.dto.RestTemplateResponse;
import com.f1soft.campaign.common.exception.handler.RestTemplateExceptionHandler;
import com.f1soft.campaign.common.exception.handler.RestTemplateResponseErrorHandler;
import com.f1soft.campaign.common.util.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.annotation.RequestScope;

@Slf4j
@RequestScope
@Component
public class RestNxTemplate {

    @Autowired
    protected RestTemplate restTemplate;

    //if not will take default configuration defined in project configuration
    public void setRestTemplate(ClientHttpRequestFactory httpRequestFactory) {
        this.restTemplate.setRequestFactory(HttpUtil.clientHttpRequestFactory(10, 60));
        //this.restTemplate.setRequestFactory(httpRequestFactory);
        this.restTemplate.setErrorHandler(new RestTemplateResponseErrorHandler());
    }

    public void setRestTemplate(int connectTimeout, int readTimeout) {

        this.restTemplate.setRequestFactory(HttpUtil.clientHttpRequestFactory(connectTimeout, readTimeout));
        this.restTemplate.setErrorHandler(new RestTemplateResponseErrorHandler());
    }

    public static String buildUrl(String baseUrl, String endPath) {
        return baseUrl.concat(endPath);
    }

    public <T> RestTemplateResponse doPost(String url, HttpEntity httpEntity, ParameterizedTypeReference<T> responseType) {
        log.debug("Triggering URL : {} with body : {}", url, httpEntity.getBody());
        RestTemplateResponse restTemplateResponse = new RestTemplateResponse<>();

        try {
            ResponseEntity response = restTemplate
                    .exchange(
                            url,
                            HttpMethod.POST,
                            httpEntity,
                            responseType);

            log.debug("Http response code : " + response.getStatusCode().name()+" ,Response : "+response.getBody());

            restTemplateResponse.setHttpStatus(response.getStatusCode());
            restTemplateResponse.setObtained(true);
            restTemplateResponse.setObj(response.getBody());

            return restTemplateResponse;
        } catch (HttpClientErrorException e) {
            log.error("error : {}", e.getMessage());
            if (log.isDebugEnabled()) {
                log.error("Error log :", e);
            }
            log.error("Http response code : " + e.getStatusCode().name());
            String errorPayload = e.getResponseBodyAsString();
            log.error("Error response : " + errorPayload);
            return RestTemplateExceptionHandler.exceptionHandler(e);
        } catch (RestClientException e) {
            String errorPayload = e.getMessage();
            log.error("Error response : " + errorPayload);
            if (log.isDebugEnabled()) {
                log.error("Error log :", e);
            }
            return RestTemplateExceptionHandler.exceptionHandler(e);
        } catch (Exception e) {
            log.error("error : {}", e.getMessage());
            if (log.isDebugEnabled()) {
                log.error("Error log :", e);
            }
            return RestTemplateExceptionHandler.exceptionHandler(e);
        }
    }

    public <T> RestTemplateResponse<T> doGet(String url, HttpEntity httpEntity, ParameterizedTypeReference<T> responseType) {
        log.debug("Triggering URL : {} with body : {}", url, httpEntity.getBody());
        RestTemplateResponse restTemplateResponse = new RestTemplateResponse();

        try {
            ResponseEntity response = restTemplate
                    .exchange(
                            url,
                            HttpMethod.GET,
                            httpEntity,
                            responseType);

            log.debug("Http response code : " + response.getStatusCode().name()+" ,Response : "+response.getBody());

            restTemplateResponse.setHttpStatus(response.getStatusCode());
            restTemplateResponse.setObtained(true);
            restTemplateResponse.setObj(response.getBody());

            return restTemplateResponse;
        } catch (HttpClientErrorException e) {
            //log.error("error : ", e);
            log.error("Http response code : " + e.getStatusCode().name());
            String errorPayload = e.getResponseBodyAsString();
            log.error("Error response : " + errorPayload);
            log.error("Error msg : " + e.getMessage());
            return RestTemplateExceptionHandler.exceptionHandler(e);
        } catch (RestClientException e) {
            log.error("error : ", e);
            return RestTemplateExceptionHandler.exceptionHandler(e);
        } catch (Exception e) {
            log.error("error : ", e);
            return RestTemplateExceptionHandler.exceptionHandler(e);
        }
    }
}
