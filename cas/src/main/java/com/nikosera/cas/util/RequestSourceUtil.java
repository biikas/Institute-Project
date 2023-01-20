package com.nikosera.cas.util;

import javax.servlet.http.HttpServletRequest;

public class RequestSourceUtil {

    public static String getUserIp(HttpServletRequest request) {
        String remoteAddr = "";
        if (request != null) {
            remoteAddr = request.getHeader("X-FORWARDED-FOR");
            if (remoteAddr == null || "".equals(remoteAddr)) {
                remoteAddr = request.getRemoteAddr();
            }
        }
        return remoteAddr;
    }

    public static String getUserAgent(HttpServletRequest request) {
        String userAgent = "";
        if (request != null) {
            userAgent = request.getHeader("User-Agent");
        }
        return userAgent;
    }
}
