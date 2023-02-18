package com.college.campaign.web.password.dto;


import com.college.campaign.common.dto.ModelBase;
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
