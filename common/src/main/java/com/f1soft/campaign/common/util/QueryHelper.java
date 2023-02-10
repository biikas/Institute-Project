package com.f1soft.campaign.common.util;

import lombok.extern.slf4j.Slf4j;

import java.util.Map;

@Slf4j
public class QueryHelper {

    public static String convertedSql(String s, Map<String, Object> parameterMap) {
        log.info("Parameter map : " + parameterMap);
        for (Map.Entry<String, Object> entry : parameterMap.entrySet()) {
            s = s.replace("{" + entry.getKey() + "}", entry.getValue() != null ? (String) entry.getValue() : "");
        }
        log.info("Final query : " + s);
        return s;
    }

}
