package com.f1soft.campaign.common.hmac;


import lombok.extern.slf4j.Slf4j;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

/*
 * @Author Rashim Dhaubanjar
 */
@Slf4j
public class HmacOldBanksmart extends HmacSignatureBuilder {

    private static final byte delimeter = '\n';

    private String scheme;
    private String host;
    private String method = "POST";
    private String resource;
    private String contentType = "application/json";

    public HmacOldBanksmart() {
        super(delimeter);
    }

    private void init(URL url) {
        this.scheme = url.getProtocol();
        this.host = url.getHost();
        this.method = "POST";
        this.resource = url.getPath();
        this.contentType = "application/json";
    }

    @Override
    public byte[] build() {
        init(url);
        Objects.requireNonNull(algorithm, "algorithm");
        Objects.requireNonNull(scheme, "scheme");
        Objects.requireNonNull(host, "host");
        Objects.requireNonNull(method, "method");
        Objects.requireNonNull(resource, "resource");
        Objects.requireNonNull(contentType, "contentType");
        Objects.requireNonNull(apiKey, "apiKey");
        Objects.requireNonNull(payload, "payload");

        try {

            final Mac digest = Mac.getInstance(algorithm);
            SecretKeySpec secretKey = new SecretKeySpec(apiSecret, algorithm);
            digest.init(secretKey);
            digest.update(DELIMITER);
            digest.update(method.getBytes(StandardCharsets.UTF_8));
            digest.update(DELIMITER);
            digest.update(scheme.getBytes(StandardCharsets.UTF_8));
            digest.update(DELIMITER);
            digest.update(host.getBytes(StandardCharsets.UTF_8));
            digest.update(DELIMITER);
            digest.update(resource.getBytes(StandardCharsets.UTF_8));
            digest.update(DELIMITER);
            digest.update(contentType.getBytes(StandardCharsets.UTF_8));
            digest.update(DELIMITER);
            digest.update(apiKey.getBytes(StandardCharsets.UTF_8));
            digest.update(DELIMITER);

            if (nonce != null) {
                digest.update(nonce.getBytes(StandardCharsets.UTF_8));
            }

            digest.update(DELIMITER);
            digest.update(payload);
            digest.update(DELIMITER);
            final byte[] signatureBytes = digest.doFinal();
            digest.reset();

            return signatureBytes;

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Can't create signature: " + e.getMessage(), e);
        } catch (InvalidKeyException ex) {
            log.error("Invalid Key Exception");
        }

        return null;
    }


}
