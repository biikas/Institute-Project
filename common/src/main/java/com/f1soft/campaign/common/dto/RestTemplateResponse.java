package com.f1soft.campaign.common.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class RestTemplateResponse<T> extends ModelBase {
    private boolean obtained;
    private String errorMessage;
    private String errorCode;
    private String developerMessage;
    private T obj;
    private HttpStatus httpStatus;

    public static String READ_TIMEOUT = "-10";
    public static String CONNECT_TIMEOUT = "-1";
    public static String INTERNAL_SERVER_ERROR = "1";

}
