package com.f1soft.campaign.web.password.dto;

import com.f1soft.campaign.common.dto.ModelBase;
import lombok.Getter;
import lombok.Setter;

/**
 * @Author Rashim Dhaubanjar
 * @Since 1.0
 */
@Getter
@Setter
public class PasswordDetail extends ModelBase {

    private boolean allowOldPassword = true;
    /**
     * new or current password.
     */
    private String password;
    private String oldPassword;

}