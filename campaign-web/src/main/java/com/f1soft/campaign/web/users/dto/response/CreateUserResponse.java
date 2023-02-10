package com.f1soft.campaign.web.users.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateUserResponse {
    private String username;
    private String password;
    private String mobileNumber;
    private String name;
    private String emailAddress;
    private Character active;
    private String createdDate;
    private String createdBy;
}
