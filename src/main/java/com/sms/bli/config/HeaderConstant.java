package com.sms.bli.config;

import com.google.common.collect.ImmutableMap;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class HeaderConstant {
    public static final String AUTHORIZATION = "Authorization";
    public static final String DATE = "date";
    public static final String CONTENT_TYPE = "content-type";
    public static final String X_AUTH_TOKEN = "x-auth-token";
    public static final String X_REQUEST_CHANNEL = "x-request-channel";
    public static final String X_BROWSER = "x-browser";
    public static final String X_DEVICE_OS = "x-device-os";
    public static final String X_FINGERPRINT = "x-fingerprint";
    public static final String X_DEVICE_TYPE = "x-device-type";
    public static final String X_DEVICE_ID = "x-device-id";


    public static Map<String, String> headerMap = ImmutableMap.<String, String>builder()
            .put("AUTHORIZATION", AUTHORIZATION)
            .put("DATE", DATE)
            .put("CONTENT_TYPE", CONTENT_TYPE)
            .put("X_AUTH_TOKEN", X_AUTH_TOKEN)
            .put("X_REQUEST_CHANNEL", X_REQUEST_CHANNEL)
            .put("X_BROWSER", X_BROWSER)
            .put("X_DEVICE_OS", X_DEVICE_OS)
            .put("X_FINGERPRINT", X_FINGERPRINT)
            .put("X_DEVICE_TYPE", X_DEVICE_TYPE)
            .put("X_DEVICE_ID", X_DEVICE_ID)
            .build();

    public static List<String> getHeaderValue() {
        return headerMap.entrySet().stream().map(Map.Entry::getValue).collect(Collectors.toList());
    }
}
