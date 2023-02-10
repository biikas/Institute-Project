package com.f1soft.campaign.common.hmac;

/*
 * @Author Rashim Dhaubanjar
 */

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.UUID;

@Slf4j
@Getter
public class HmacBuilder<T> {

    public static final String HMAC_SHA_512 = "HmacSHA512";

    private final HmacSignatureBuilder signatureBuilder;

    public HmacBuilder() {
        this.signatureBuilder = new HmacSpace();
    }

    public HmacBuilder(HmacSignatureBuilder signatureBuilder) {
        this.signatureBuilder = signatureBuilder;
    }

    public String getHeader(String strUrl, T objPayload, String apiKey, String apiSecret) {

        signatureBuilder.algorithm(HMAC_SHA_512);
        try {
            URL url = new URL(strUrl);
            String payload = new ObjectMapper().writeValueAsString(objPayload);

            String nonce = UUID.randomUUID().toString();

            log.debug("Protocol : " + url.getProtocol());
            log.debug("Host : " + url.getHost());
            log.debug("Payload : " + payload);
            log.debug("Path : " + url.getPath());

            final HmacSignatureBuilder builder = getSignatureBuilder()
                    .algorithm(HMAC_SHA_512)
                    .nonce(nonce)
                    .apiKey(apiKey)
                    .apiSecret(apiSecret)
                    .url(url)
                    .payload(payload.getBytes(StandardCharsets.UTF_8));

            byte[] digest = builder.build();

            String header = "HmacSHA512 " + apiKey + ":" + nonce + ":" + Base64.getEncoder().encodeToString(digest);

            log.debug(header);
            return header;
        } catch (MalformedURLException | JsonProcessingException e) {
            log.error("Error: ", e);
            log.error("Failed to build Hmac header.");
            return null;
        }
    }
}
