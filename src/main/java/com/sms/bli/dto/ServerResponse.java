package com.sms.bli.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Bikash Shah
 */
@Getter
@Setter
public class ServerResponse {

    protected boolean success;
    private Object obj;
    private String message;
    private String code;

}
