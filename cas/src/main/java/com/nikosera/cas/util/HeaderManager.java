package com.nikosera.cas.util;

import com.nikosera.cas.config.properties.CasFileConfig;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

/**
 * @author Narayan Joshi
 * @email narenzoshi@gmail.com
 */
@Component
@AllArgsConstructor
public class HeaderManager {

    private final CasFileConfig casFileConfig;

    public HttpHeaders buildAuthenticationHeader(String token) {
        HttpHeaders headers = new HttpHeaders();
        String headerValue = casFileConfig.getAuthBearerPrefix() + " " + token;
        headers.add(casFileConfig.getJwtHeader(), headerValue);
        return headers;
    }
}
