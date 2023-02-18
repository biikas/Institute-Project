package com.college.campaign.web.password.dto;


import com.college.campaign.common.dto.ModelBase;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ChangePasswordRequest extends ModelBase {

    private String oldPassword;
    private String newPassword;
}
