package com.f1soft.campaign.web.password.dto;


import com.f1soft.campaign.common.dto.ModelBase;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ChangePasswordRequest extends ModelBase {

    private String oldPassword;
    private String newPassword;
}
