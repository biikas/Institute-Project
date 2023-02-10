package com.f1soft.campaign.client.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @Author Nitesh Poudel
 */
@Getter
@Setter
public class UserInfoDTO {

    //user information required for email send
    private String emailAddress;
    private String name;
}
