package com.nikosera.cas.util;

import com.nikosera.cas.config.properties.CasFileConfig;
import com.nikosera.common.exception.UnauthorizedException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

import static com.nikosera.common.constant.MsgConstant.Authorization.AUTH_HEADER_NOT_FOUND;
import static com.nikosera.common.constant.MsgConstant.Authorization.AUTH_HEADER_NOT_VALID;
import static java.util.Optional.ofNullable;

/**
 * @author Narayan Joshi
 * @email narenzoshi@gmail.com
 */
@Slf4j
@Component
@AllArgsConstructor
public class BearerTokenHeaderUtil {

    private final CasFileConfig casFileConfig;

    public String extractToken(HttpServletRequest request) {
        String param = ofNullable(request.getHeader(casFileConfig.getJwtHeader()))
                .orElseThrow(() -> new UnauthorizedException(AUTH_HEADER_NOT_FOUND));
        String prefix = casFileConfig.getAuthBearerPrefix() + " ";
        String token = "";
        if (param.startsWith(prefix)) {
            token = param.substring(prefix.length());
        } else {
            log.error("Authentication Failed: couldn't find token after Bearer");
            throw new UnauthorizedException(AUTH_HEADER_NOT_VALID);
        }
        return token;
    }
}
