package com.f1soft.campaign.common.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ServerResponse extends ModelBase {

    private boolean success;
    private String message;
    private String code;
    private Object obj;
    public String unicodeMessage;

    public ServerResponse(boolean success) {
        this.success = success;
    }
}
