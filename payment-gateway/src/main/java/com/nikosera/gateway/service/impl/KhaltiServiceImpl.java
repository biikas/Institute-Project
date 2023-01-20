package com.nikosera.gateway.service.impl;

import com.nikosera.common.dto.GenericResponse;
import com.nikosera.gateway.dto.PaymentRequestDto;
import com.nikosera.gateway.service.KhaltiService;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;

/**
 * @author narayanjoshi
 * @email <narayan.joshi>
 * Created Date: 12/6/22
 */

@Slf4j
public class KhaltiServiceImpl implements KhaltiService {

    @Value("${khalti.baseUrl}")
    private String baseUrl;

    @Value("${khalti.secretKey}")
    private String khaltiSecretKey;

    @Value("${callgram.returnUrl}")
    private String callgramReturnUrl;

    @Value("${callgram.url}")
    private String callgramUrl;


    @Autowired
    private ApiInvoker apiInvoker;

    @Override
    public GenericResponse initiatePayment(PaymentRequestDto requestDto) {
        log.info("Initiating Khalti Payment Request for order: {}, initiator: {}", requestDto.getOrderId(), requestDto.getInitiatorMobileNumber());
        GenericResponse genericResponse = new GenericResponse();

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("return_url", callgramReturnUrl);
        jsonObject.put("website_url", callgramUrl);
        jsonObject.put("amount", requestDto.getAmount());
        jsonObject.put("purchase_order_id", requestDto.getOrderId());
        jsonObject.put("purchase_order_name", requestDto.getOrderName());

        JSONObject customerObject = new JSONObject();
        customerObject.put("name", requestDto.getInitiatorName());
        customerObject.put("email", requestDto.getInitiatorEmail());
        customerObject.put("phone", requestDto.getInitiatorMobileNumber());

        jsonObject.put("customer_info", customerObject);


        String requestJson = jsonObject.toString();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add(HttpHeaders.AUTHORIZATION, "Key " + khaltiSecretKey);

        HttpEntity<String> entity = new HttpEntity<String>(requestJson,headers);

        genericResponse = apiInvoker.initiatePayment(baseUrl, entity, jsonObject);

        return genericResponse;
    }


    private HttpEntity<Object> getRequest(String payload) {
        if (payload != null && !payload.isEmpty()) {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.add(HttpHeaders.AUTHORIZATION, "Key " + khaltiSecretKey);
            return new HttpEntity<>(payload, headers);
        }
        return null;
    }


}
