package com.nikosera.common.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

/**
 * @author narayanjoshi
 * @email <narayan.joshi>
 * Created Date: 12/7/22
 */
@Data
public class RestTemplateException extends AbstractRuntimeException {

    private HttpStatus httpStatus;
    private String code;

    public RestTemplateException(String message, HttpStatus httpStatus) {
        super(message, String.valueOf(httpStatus.value()));
        this.httpStatus = httpStatus;
    }

    public RestTemplateException(String message, HttpStatus httpStatus, String code) {
        super(message, String.valueOf(httpStatus.value()));
        this.httpStatus = httpStatus;
        this.code = code;
    }

    public RestTemplateException(String message, String httpCode) {
        super(message, httpCode);
    }
}

