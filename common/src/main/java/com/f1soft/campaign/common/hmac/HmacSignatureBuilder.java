package com.f1soft.campaign.common.hmac;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Objects;

@Slf4j
@NoArgsConstructor
public class HmacSignatureBuilder {

    protected static final String HMAC_SHA_512 = "HmacSHA512";
    protected String algorithm = HMAC_SHA_512;

    protected byte DELIMITER = ' ';
    protected String nonce;
    protected String apiKey;
    protected byte[] apiSecret;
    protected byte[] payload;

    protected URL url;

    public String getAlgorithm() {
        return HMAC_SHA_512;
    }

    public HmacSignatureBuilder(byte delimeter) {
        this.DELIMITER = delimeter;
    }

    public HmacSignatureBuilder algorithm(String algorithm) {
        this.algorithm = algorithm;
        return this;
    }

    public HmacSignatureBuilder url(URL url) {
        this.url = url;
        return this;
    }

    public HmacSignatureBuilder apiKey(String key) {
        this.apiKey = key;
        return this;
    }

    public HmacSignatureBuilder nonce(String nonce) {
        this.nonce = nonce;
        return this;
    }

    public HmacSignatureBuilder apiSecret(byte[] secretBytes) {
        this.apiSecret = secretBytes;
        return this;
    }

    public HmacSignatureBuilder apiSecret(String secretString) {
        this.apiSecret = secretString.getBytes(StandardCharsets.UTF_8);
        return this;
    }

    public HmacSignatureBuilder payload(byte[] payloadBytes) {
        this.payload = payloadBytes;
        return this;
    }

    public byte[] build() {
        Objects.requireNonNull(algorithm, "algorithm");
        Objects.requireNonNull(apiKey, "apiKey");
        Objects.requireNonNull(payload, "payload");

        try {

            final Mac digest = Mac.getInstance(algorithm);
            SecretKeySpec secretKey = new SecretKeySpec(apiSecret, algorithm);
            digest.init(secretKey);
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

    public boolean isHashEquals(byte[] expectedSignature) {
        final byte[] signature = build();

        String base64Signature = Base64.getEncoder().encodeToString(signature);
        String base64Expected = Base64.getEncoder().encodeToString(expectedSignature);
        log.info("Base64 signature : " + base64Signature);
        log.info("Base64 expected : " + base64Expected);
        return base64Signature.equals(base64Expected);
    }
}
