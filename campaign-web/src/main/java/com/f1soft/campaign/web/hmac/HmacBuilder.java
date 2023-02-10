package com.f1soft.campaign.web.hmac;

import com.f1soft.campaign.entities.model.Client;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.UUID;


@Slf4j
public class HmacBuilder {

    private HmacBuilder() {}

    public static final String HMAC_SHA_512 = "HmacSHA512";
    public static final byte DELIMITER = ' ';


    public static String getHeader(String strUrl, Object objPayload, Client client) {

        try {
            URL url = new URL(strUrl);
            String payload = new ObjectMapper().writeValueAsString(objPayload);


            String nonce = UUID.randomUUID().toString();

            log.info("Protocol : " + url.getProtocol());
            log.info("Host : " + url.getHost());
            log.info("Payload : " + payload);
            log.info("Path : " + url.getPath());

            final HmacSignatureBuilder signatureBuilder = new HmacSignatureBuilder()
                    .algorithm(HMAC_SHA_512)
                    .nonce(nonce)
                    .apiKey(client.getUsername())
                    .apiSecret(client.getApiSecret())
                    .payload(payload.getBytes(StandardCharsets.UTF_8));

            byte[] digest = signatureBuilder.build();

            String header = "HmacSHA512 " + client.getUsername() + ":" + nonce + ":" + Base64.getEncoder().encodeToString(digest);

            log.info(header);
            return header;
        } catch (MalformedURLException | JsonProcessingException e) {
            log.error("Error: ", e);
            log.debug("Failed to build Hmac header.");
            return null;
        }
    }
}
