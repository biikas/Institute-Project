package com.college.campaign.web.hmac;

public interface CredentialsProvider {

    byte[] getApiSecret(String apiKey);
}
