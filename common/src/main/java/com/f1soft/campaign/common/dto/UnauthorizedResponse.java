package com.f1soft.campaign.common.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UnauthorizedResponse extends ModelBase {

    /**
     * Success can also be 200 status errorCode
     */
    private boolean success;
    private String message;
    private String unicodeMessage;
    private String sessionTimeout;
    private String code;
}
