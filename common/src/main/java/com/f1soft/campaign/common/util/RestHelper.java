package com.f1soft.campaign.common.util;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.util.Arrays;

public class RestHelper {

    public static <T> HttpEntity<T> buildEntity(T obj) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity entity = new HttpEntity<>(obj, headers);
        return entity;
    }

    public static <T> HttpEntity<T> buildXmlEntity(T obj) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_XML));
        HttpEntity entity = new HttpEntity<>(obj, headers);
        return entity;
    }

    public static <T> HttpEntity<T> buildXmlEntity() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_XML));
        HttpEntity entity = new HttpEntity<>(headers);
        return entity;
    }

    public static <T> HttpEntity<T> buildHmacEntity(T obj, HttpHeaders header) {
        header.setContentType(MediaType.APPLICATION_JSON);
        header.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity entity = new HttpEntity<>(obj, header);
        return entity;
    }
}
