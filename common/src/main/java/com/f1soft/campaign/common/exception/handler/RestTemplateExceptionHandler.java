package com.f1soft.campaign.common.exception.handler;

import com.f1soft.campaign.common.dto.RestTemplateResponse;
import com.fasterxml.jackson.databind.JsonMappingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.json.JsonParseException;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.RestClientException;

import java.io.IOException;

public class RestTemplateExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(RestTemplateExceptionHandler.class);

    public static RestTemplateResponse exceptionHandler(Exception e) {
        RestTemplateResponse restTemplateResponse = new RestTemplateResponse();
        restTemplateResponse.setObtained(false);
        if (e instanceof RestClientException) {
            log.error("Rest client exception : {}" , e.getMessage());
            restTemplateResponse.setErrorCode(RestTemplateResponse.INTERNAL_SERVER_ERROR);
            restTemplateResponse.setErrorMessage("Internal Server Error");
            restTemplateResponse.setDeveloperMessage(((RestClientException) e).getMessage());
            restTemplateResponse.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        } else if (e instanceof IOException) {
            log.error("IO Exception : {}", e.getMessage());
            restTemplateResponse.setErrorCode(RestTemplateResponse.READ_TIMEOUT);
            restTemplateResponse.setErrorMessage("System Exception");
            restTemplateResponse.setDeveloperMessage(e.getMessage());
            restTemplateResponse.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        } else if (e instanceof JsonMappingException) {
            log.error("Json mapping Exception : {}", e.getMessage());
            restTemplateResponse.setErrorCode(RestTemplateResponse.READ_TIMEOUT);
            restTemplateResponse.setErrorMessage("System Exception");
            restTemplateResponse.setDeveloperMessage(e.getMessage());
            restTemplateResponse.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        } else if (e instanceof JsonParseException) {
            log.error("Json parse exception : {}", e.getMessage());
            restTemplateResponse.setErrorCode(RestTemplateResponse.READ_TIMEOUT);
            restTemplateResponse.setErrorMessage("System Exception");
            restTemplateResponse.setDeveloperMessage(e.getMessage());
            restTemplateResponse.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        } else {
            String message = e.getMessage();
            if (message.contains("java.net.SocketTimeoutException: connect timed out")) {
                log.error("Connection time out exception : {}", message);
                restTemplateResponse.setErrorMessage("System Exception");
                restTemplateResponse.setErrorCode(RestTemplateResponse.CONNECT_TIMEOUT);
                restTemplateResponse.setHttpStatus(HttpStatus.REQUEST_TIMEOUT);
            } else if (message.contains("java.net.SocketTimeoutException: Read timed out")) {
                log.error("Read time out exception : {}", message);
                restTemplateResponse.setErrorMessage("Connection Time out");
                restTemplateResponse.setErrorCode(RestTemplateResponse.READ_TIMEOUT);
                restTemplateResponse.setHttpStatus(HttpStatus.REQUEST_TIMEOUT);
            } else {
                log.error("Generic exception : {}", message);
                restTemplateResponse.setErrorMessage("Internal Server Error");
                restTemplateResponse.setErrorCode(RestTemplateResponse.INTERNAL_SERVER_ERROR);
                restTemplateResponse.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            }
            restTemplateResponse.setDeveloperMessage(message);
        }
        return restTemplateResponse;
    }
}
