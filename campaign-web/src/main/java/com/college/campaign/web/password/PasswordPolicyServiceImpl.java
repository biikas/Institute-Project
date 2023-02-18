package com.college.campaign.web.password;


import com.college.campaign.common.config.application.SystemConfig;
import com.college.campaign.common.constant.MsgConstant;
import com.college.campaign.common.dto.ServerResponse;
import com.college.campaign.common.util.ResponseMsg;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
public class PasswordPolicyServiceImpl implements PasswordPolicyService {

    @Autowired
    private SystemConfig systemConfig;

    @Override
    public ServerResponse passwordPolicy() {

//        PasswordPolicyResponse policyResponse = new PasswordPolicyResponse();

        String[] policyInfo = systemConfig.adminPasswordConfig(AdminPasswordConfigConstant.POLICY_INFO).split("\\\\n");
        List<String> passwordPolicy = Arrays.asList(policyInfo);
//
//        policyResponse.setPasswordPolicy(passwordPolicy);
//        policyResponse.setMinLength(Integer.valueOf(systemConfig.adminPasswordConfig(AdminPasswordConfigConstant.MIN_LENGTH)));
//        policyResponse.setMaxLength(Integer.valueOf(systemConfig.adminPasswordConfig(AdminPasswordConfigConstant.MAX_LENGTH)));

        return ResponseMsg.successResponse(MsgConstant.Data.SUCCESS);
    }


}
