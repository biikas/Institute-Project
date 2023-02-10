package com.f1soft.campaign.web.password;


import com.f1soft.campaign.common.config.application.SystemConfig;
import com.f1soft.campaign.common.constant.MsgConstant;
import com.f1soft.campaign.common.dto.ServerResponse;
import com.f1soft.campaign.common.util.MessageComposer;
import com.f1soft.campaign.common.util.ResponseMsg;
import com.f1soft.campaign.entities.model.AdminPasswordChangeLog;
import com.f1soft.campaign.entities.model.ApplicationUser;
import com.f1soft.campaign.repository.AdminPasswordChangeLogRepository;
import com.f1soft.campaign.repository.ApplicationUserRepository;
import com.f1soft.campaign.web.password.dto.PasswordDetail;
import com.f1soft.campaign.web.password.dto.PasswordPolicy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.annotation.RequestScope;

import java.util.Date;
import java.util.List;

/**
 * @author Rashim Dhaubanjar
 */
@Slf4j
@Component
@RequestScope
public class PasswordManager {

    @Autowired
    private SystemConfig systemConfig;
    @Autowired
    private PasswordValidator passwordValidator;
    @Autowired
    private PasswordConfigMapper passwordConfigMapper;
    @Autowired
    private AdminPasswordChangeLogRepository adminPasswordChangeLogRepository;
    @Autowired
    private ApplicationUserRepository applicationUserRepository;

    private PasswordDetail passwordDetail;
    private ApplicationUser applicationUser;
    private ServerResponse serverResponse;

    public void init(ApplicationUser applicationUser, PasswordDetail passwordDetail) {
        this.applicationUser = applicationUser;
        this.passwordDetail = passwordDetail;
        this.serverResponse = new ServerResponse();
    }

    public ServerResponse save(PasswordDetail passwordDetail, ApplicationUser applicationUser) {

        init(applicationUser, passwordDetail);
        ServerResponse serverResponse = isValid(passwordDetail.getPassword());
        if (serverResponse.isSuccess()) {
            if (!passwordDetail.isAllowOldPassword()) {
                serverResponse = checkTopPassword(applicationUser.getId());
            }
            if (serverResponse.isSuccess()) {
                serverResponse = updatePassword();
            }
        }
        return serverResponse;
    }

    public ServerResponse changePassword(ApplicationUser applicationUser, PasswordDetail passwordDetail) {

        init(applicationUser, passwordDetail);
        //validate password
        ServerResponse serverResponse = validChangePassword(applicationUser, passwordDetail);
        if (serverResponse.isSuccess()) {
            serverResponse = updatePassword();
        }
        return serverResponse;
    }

    private ServerResponse validChangePassword(ApplicationUser applicationUser, PasswordDetail passwordDetail) {

        ServerResponse serverResponse = validateOldPassword();

        if (serverResponse.isSuccess()) {
            PasswordPolicy passwordPolicy = passwordConfigMapper.convertToAdminPasswordPolicyDTO();
            serverResponse = validatePolicy(passwordDetail.getPassword(), passwordPolicy);

            if (serverResponse.isSuccess()) {
                serverResponse = checkTopPassword(applicationUser.getId());
            }
        }
        return serverResponse;
    }

    private ServerResponse updatePassword() {
        applicationUser.setLastPasswordChanged(new Date());
        applicationUser.setPassword(BCrypt.hashpw(String.valueOf(passwordDetail.getPassword()), BCrypt.gensalt()));
        applicationUserRepository.save(applicationUser);

        //log password change
        storePasswordUpdateLog();
        return ResponseMsg.successResponse(MsgConstant.Password.PASSWORD_MODIFY_SUCCESS);
    }

    public void storePasswordUpdateLog() {
        AdminPasswordChangeLog adminPasswordChangeLog = new AdminPasswordChangeLog();
        adminPasswordChangeLog.setPassword(BCrypt.hashpw(String.valueOf(passwordDetail.getPassword()), BCrypt.gensalt()));
        adminPasswordChangeLog.setUserId(applicationUser);
        adminPasswordChangeLog.setRecordedDate(new Date());

        adminPasswordChangeLogRepository.save(adminPasswordChangeLog);
    }

    protected ServerResponse validateOldPassword() {

        boolean valid = BCrypt.checkpw(passwordDetail.getOldPassword(), applicationUser.getPassword());
        if (valid) {
            return new ServerResponse(true);
        } else {
            log.error("Old password is incorrect.");
            return ResponseMsg.failureResponse(MsgConstant.Password.INVALID_PASSWORD_MESSAGE);
        }
    }


    protected ServerResponse validatePolicy(String password, PasswordPolicy passwordPolicy) {
        ServerResponse serverResponse = new ServerResponse();
        if (password.contains(" ")) {
            log.error("password contains space");
            serverResponse.setSuccess(false);
            serverResponse = MessageComposer.compose(serverResponse, MsgConstant.Password.PASSWORD_POLICY_NOT_MATCHED,
                    MessageComposer.getParameters());

            return serverResponse;
        }
        if (!StringUtils.isEmpty(password)) {

            if (passwordValidator.isValidPassword(password, passwordPolicy)) {
                serverResponse.setSuccess(true);
            } else {
                log.error(passwordValidator.getMessage());

                serverResponse.setSuccess(false);
                serverResponse.setMessage(passwordValidator.getMessage());
            }
        } else {
            log.error("password is empty");
            serverResponse.setSuccess(false);
            serverResponse = MessageComposer.compose(serverResponse, MsgConstant.Password.INVALID_PASSWORD_LENGTH,
                    MessageComposer.getParameters());
        }
        return serverResponse;
    }

    private ServerResponse checkTopPassword(long userId) {
        ServerResponse serverResponse = new ServerResponse();
        int count = Integer.parseInt(systemConfig.adminPasswordConfig(AdminPasswordConfigConstant.LAST_PASSWORD_RESTRICT_COUNT));
        if (topNPassword(count)) {
            serverResponse.setSuccess(true);
            return serverResponse;
        } else {
            log.error("Recent used password is set");
            return ResponseMsg.failureResponse(MsgConstant.Password.RESTRICT_SAME_PASSWORD);
        }
    }

    private boolean topNPassword(int count) {

        List<AdminPasswordChangeLog> adminPasswordChangeLogList = adminPasswordChangeLogRepository.recentPasswordChangeLogs(applicationUser.getId(), PageRequest.of(0, count)).toList();
        if (!adminPasswordChangeLogList.isEmpty()) {
            for (AdminPasswordChangeLog changePasswordLog : adminPasswordChangeLogList) {
                if (BCrypt.checkpw(passwordDetail.getPassword(), changePasswordLog.getPassword())) {
                    return false;
                }
            }
        }
        return true;
    }

    protected ServerResponse match(PasswordDetail passwordDetail, ApplicationUser applicationUser) {
        boolean valid = BCrypt.checkpw(passwordDetail.getPassword(), applicationUser.getPassword());
        if (valid) {
            serverResponse.setSuccess(true);
        } else {
            boolean lowerPin = BCrypt.checkpw(passwordDetail.getPassword().trim().toLowerCase(), applicationUser.getPassword());
            if (lowerPin) {
                serverResponse.setSuccess(true);
                return serverResponse;
            }
            log.error("invalid login password provided.");
            return ResponseMsg.failureResponse(MsgConstant.Password.INVALID_PASSWORD_MESSAGE);
        }
        return serverResponse;
    }

    public ServerResponse isValid(String password) {
        PasswordPolicy passwordPolicy = passwordConfigMapper.convertToAdminPasswordPolicyDTO();
        return validatePolicy(password, passwordPolicy);
    }

}
