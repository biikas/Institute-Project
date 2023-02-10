package com.f1soft.campaign.web.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomCBSConnectionDTO {

    private Long id;
    private String cbsConnectionURL;
    private String cbsDriverName;
    private String cbsUsername;
    private String cbsPassword;
    private char active;
    private String createdBy;
    private String createdDate;
    private String modifiedBy;
    private String modifiedDate;
}
