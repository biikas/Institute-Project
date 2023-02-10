package com.f1soft.campaign.web.users.dto.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;

@Getter
@ToString
public class CreateUserRequest {
    @NotEmpty
    private String userName;
    @NotEmpty
    private String password;
    private String mobileNumber;
    @NotEmpty
    private String name;
    private String emailAddress;
}
