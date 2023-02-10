package com.f1soft.campaign.common.util;

import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;

public class HttpUtil {

    public static ClientHttpRequestFactory clientHttpRequestFactory(int connectTimeout, int readTimeout) {
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        factory.setReadTimeout(readTimeout * 1000);
        factory.setConnectTimeout(connectTimeout * 1000);
        return factory;
    }
}
