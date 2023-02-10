package com.f1soft.campaign.web.login.dto;

import com.f1soft.campaign.common.dto.ModelBase;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

/*
 * @Author Rashim Dhaubanjar
 */

@Getter
@Setter
public class LoginRequest extends ModelBase {
    @NotBlank
    private String username;
    @NotBlank
    private String password;
}
