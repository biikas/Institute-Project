package com.nikosera.gateway.service.impl;

import com.nikosera.common.dto.GenericResponse;
import com.nikosera.common.exception.RestTemplateException;
import com.nikosera.gateway.dto.KhaltiPaymentResponse;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

/**
 * @author narayanjoshi
 * @email <narayan.joshi>
 * Created Date: 12/7/22
 */

@Slf4j
public class ApiInvoker {

    @Autowired
    private RestTemplate restTemplate;

    public GenericResponse initiatePayment(String baseUrl, HttpEntity<String> entity, JSONObject payload) {
        GenericResponse genericResponse = new GenericResponse();
        try {

            ResponseEntity<KhaltiPaymentResponse> response = restTemplate.postForEntity(baseUrl, entity, KhaltiPaymentResponse.class);

            if (response.getStatusCode() == HttpStatus.OK) {
                genericResponse.setSuccess(true);
                genericResponse.setMessage("Khalit Payment Initiate Success");
                genericResponse.setStatusCode("0");
                genericResponse.setData(response);
            } else if (response.getStatusCode() == HttpStatus.REQUEST_TIMEOUT || response.getStatusCode() == HttpStatus.GATEWAY_TIMEOUT) {
                genericResponse.setSuccess(false);
                genericResponse.setStatusCode("-1");
                genericResponse.setMessage("Payment Failure");
            } else {
                genericResponse.setSuccess(false);
                genericResponse.setStatusCode("1");
                genericResponse.setMessage("Payment Failure");
            }
        } catch (RestClientException e) {
            log.error(e.getMessage());
            genericResponse.setStatusCode("2");
            genericResponse.setMessage("Payment Failure");
        } catch (RestTemplateException re) {
            log.error("RestTemplateException:: " + re);
            genericResponse.setStatusCode("2");
            genericResponse.setMessage("Payment Failure");
        }
        return genericResponse;
    }
}

