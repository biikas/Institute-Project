package com.f1soft.campaign.web.password;


import com.f1soft.campaign.common.config.application.SystemConfig;
import com.f1soft.campaign.web.password.dto.PasswordPolicy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@SuppressWarnings({"Duplicates"})
@Component
public class PasswordConfigMapper {

    @Autowired
    private SystemConfig systemConfig;

    public PasswordPolicy convertToAdminPasswordPolicyDTO() {
        PasswordPolicy passwordPolicy = new PasswordPolicy();
        passwordPolicy.setAlphaIndex(systemConfig.adminPasswordConfig(AdminPasswordConfigConstant.APLHA_INDEX));
        passwordPolicy.setAlphaMinLength(Integer.valueOf(systemConfig.adminPasswordConfig(AdminPasswordConfigConstant.ALPHA_MIN_LENGTH)));
        passwordPolicy.setMaxLength(Integer.valueOf(systemConfig.adminPasswordConfig(AdminPasswordConfigConstant.MAX_LENGTH)));
        passwordPolicy.setMinLength(Integer.valueOf(systemConfig.adminPasswordConfig(AdminPasswordConfigConstant.MIN_LENGTH)));
        passwordPolicy.setNumberIndex(systemConfig.adminPasswordConfig(AdminPasswordConfigConstant.NUMBER_INDEX));
        passwordPolicy.setNumberMinLength(Integer.valueOf(systemConfig.adminPasswordConfig(AdminPasswordConfigConstant.NUMBER_MIN_LENGTH)));
        passwordPolicy.setSpecialCharIndex(systemConfig.adminPasswordConfig(AdminPasswordConfigConstant.SPECIAL_CHAR_INDEX));
        passwordPolicy.setSpecialCharMinLength(systemConfig.adminPasswordConfig(AdminPasswordConfigConstant.SPECIAL_CHAR_MIN_LENGTH));
        passwordPolicy.setLastPasswordRestrictionCount(systemConfig.adminPasswordConfig(AdminPasswordConfigConstant.LAST_PASSWORD_RESTRICT_COUNT));
        passwordPolicy.setPolicyInfo(systemConfig.adminPasswordConfig(AdminPasswordConfigConstant.POLICY_INFO));
        passwordPolicy.setSpecialCharacters(systemConfig.adminPasswordConfig(AdminPasswordConfigConstant.SPECIAL_CHARS));
        return passwordPolicy;
    }
}