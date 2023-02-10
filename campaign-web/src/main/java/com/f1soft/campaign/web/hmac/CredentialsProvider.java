package com.f1soft.campaign.web.hmac;

public interface CredentialsProvider {

    byte[] getApiSecret(String apiKey);
}
