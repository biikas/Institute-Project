package com.f1soft.campaign.web.password.dto;

import com.f1soft.campaign.common.dto.ModelBase;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PasswordPolicy extends ModelBase {

    private String lastPasswordRestrictionCount;
    private String policyInfo;
    private Integer maxLength;
    private String numberIndex;
    private Integer minLength;
    private String specialCharMinLength;
    private String specialCharIndex;
    private Integer expiryDays;
    private Integer numberMinLength;
    private String alphaIndex;
    private Integer alphaMinLength;
    private String specialCharacters;

}
