package com.f1soft.campaign.web.dto.request;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomCBSConnectionRequest {

    private String cbsConnectionURL;
    private String cbsDriverName;
    private String cbsUsername;
    private String cbsPassword;
    private Character active;
}
