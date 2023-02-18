package com.college.campaign.web.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TestCBSConnectionRequest {

    private String cbsConnectionURL;
    private String cbsDriverName;
    private String cbsUsername;
    private String cbsPassword;
}
