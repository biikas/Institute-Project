package com.f1soft.campaign.common.util;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JacksonUtil {

    private static final Logger logger = LoggerFactory.getLogger(JacksonUtil.class);
    private static final ObjectMapper objectMapper;

    static {
        objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public static <T> T get(String content, Class clazz) throws IOException, ClassNotFoundException {
        return (T) objectMapper.readValue(content, clazz);
    }

    public static <T> T getList(String content, Class<?> target) throws IOException, ClassNotFoundException {
        return objectMapper.readValue(content, objectMapper.getTypeFactory().constructCollectionType(List.class, Class.forName(target.getName())));
    }

    public static Map<?, ?> getMap(Object target) {
        try {
            String json = objectMapper.writeValueAsString(target);
            logger.debug("json : " + json);
            return objectMapper.readValue(json, Map.class);
        } catch (IOException e) {
            logger.error("Exception : ", e);
            return new HashMap<>();
        }
    }

    public static String getString(Object vendorResult) {
        try {
            String rawString = objectMapper.writeValueAsString(vendorResult);
            return rawString;
        } catch (JsonProcessingException ex) {
            logger.error("Exception ", ex);
        }
        return "";
    }

    public static ObjectMapper getObjectMapper() {
        return objectMapper;
    }

    public static String convertPojotoString(Object obj) {
        try {
            return JacksonUtil.getObjectMapper().writeValueAsString(obj);
        } catch (JsonProcessingException ex) {
            logger.error("Json Processing exception {}", ex);
            return "Json Processing Exception";
        }
    }
}
