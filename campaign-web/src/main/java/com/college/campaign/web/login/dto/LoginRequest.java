package com.college.campaign.web.login.dto;

import com.college.campaign.common.dto.ModelBase;
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
