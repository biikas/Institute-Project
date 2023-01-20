package com.nikosera.cas.handler;


import com.nikosera.common.builder.ResponseBuilder;
import com.nikosera.common.util.JacksonUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Narayan Joshi
 * @email narenzoshi@gmail.com
 */
@Component
public class LoginFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException {
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        JacksonUtil.getObjectMapper().writeValue(response.getWriter(), ResponseBuilder.failureResponse(e.getMessage()));
    }
}
