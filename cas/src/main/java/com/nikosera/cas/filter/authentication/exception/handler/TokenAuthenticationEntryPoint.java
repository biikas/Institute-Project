package com.nikosera.cas.filter.authentication.exception.handler;

import com.nikosera.common.builder.ResponseBuilder;
import com.nikosera.common.dto.GenericResponse;
import com.nikosera.common.util.JacksonUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;
/**
 * @author Narayan Joshi
 * @email narenzoshi@gmail.com
 */
@Component
public class TokenAuthenticationEntryPoint implements AuthenticationEntryPoint, Serializable {

    private static HttpMessageConverter<String> messageConverter = new StringHttpMessageConverter();

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
            throws IOException {

        GenericResponse genericResponse = ResponseBuilder.failureResponse(authException.getMessage());
        ServerHttpResponse outputMessage = new ServletServerHttpResponse(response);
        outputMessage.setStatusCode(HttpStatus.UNAUTHORIZED);

        messageConverter.write(JacksonUtil.getString(genericResponse), MediaType.APPLICATION_JSON, outputMessage);
    }

}
