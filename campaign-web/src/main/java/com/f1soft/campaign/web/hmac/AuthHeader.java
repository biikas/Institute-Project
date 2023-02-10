package com.f1soft.campaign.web.hmac;

import org.springframework.http.HttpHeaders;

public class AuthHeader {

    public static final String AUTHORIZATION_HEADER = HttpHeaders.AUTHORIZATION;
    private final String algorithm;
    private final String apiKey;
    private final String nonce;
    private final byte[] digest;

    public AuthHeader(String algorithm, String apiKey, String nonce, byte[] digest) {
        this.algorithm = algorithm;
        this.apiKey = apiKey;
        this.nonce = nonce;
        this.digest = digest;
    }

    public AuthHeader(String algorithm, String apiKey, byte[] digest) {
        this(algorithm, apiKey, null, digest);
    }

    public String getAlgorithm() {
        return algorithm;
    }

    public String getApiKey() {
        return apiKey;
    }

    public byte[] getDigest() {
        return digest;
    }

    public String getNonce() {
        return nonce;
    }
}
