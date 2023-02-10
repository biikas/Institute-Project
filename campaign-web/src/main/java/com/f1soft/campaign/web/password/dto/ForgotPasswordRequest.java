package com.f1soft.campaign.web.password.dto;


import com.f1soft.campaign.common.dto.ModelBase;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@Getter
@Setter
public class ForgotPasswordRequest extends ModelBase {

    @NotNull
    private Long userId;
    @NotEmpty
    private String newPassword;
}
