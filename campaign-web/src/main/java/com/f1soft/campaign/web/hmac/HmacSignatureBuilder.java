package com.f1soft.campaign.web.hmac;

import lombok.extern.slf4j.Slf4j;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Objects;

/**
 * @author Prajwol hada <prajwol.hada@f1soft.com>
 */
@Slf4j
public class HmacSignatureBuilder {

    private static final String HMAC_SHA_512 = "HmacSHA512";
    private static final byte DELIMITER = ' ';
    private String algorithm = HMAC_SHA_512;

    private String nonce;
    private String apiKey;
    private byte[] apiSecret;
    private byte[] payload;

    public String getAlgorithm() {
        return HMAC_SHA_512;
    }

    public HmacSignatureBuilder algorithm(String algorithm) {
        this.algorithm = algorithm;
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
        log.info("Signature generated from request : " + new String(signature));
        log.info("Expected signature : " + new String(expectedSignature));

        String base64Signature = Base64.getEncoder().encodeToString(signature);
        String base64Expected = Base64.getEncoder().encodeToString(expectedSignature);
        log.info("Base64 signature : " + base64Signature);
        log.info("Base64 expected : " + base64Expected);
        return base64Signature.equals(base64Expected);
    }

    public String buildAsHexString() {
        return DatatypeConverter.printHexBinary(build());
    }

    public String buildAsBase64String() {
        return DatatypeConverter.printBase64Binary(build());
    }
}
