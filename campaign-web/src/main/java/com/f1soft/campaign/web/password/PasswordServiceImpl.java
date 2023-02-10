package com.f1soft.campaign.web.password;


import com.f1soft.campaign.common.dto.ServerResponse;
import com.f1soft.campaign.entities.model.ApplicationUser;
import com.f1soft.campaign.web.password.dto.ChangePasswordRequest;
import com.f1soft.campaign.web.password.dto.PasswordDetail;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Rashim Dhaubanjar
 */
@Slf4j
@Service
public class PasswordServiceImpl implements PasswordService {

    @Autowired
    private PasswordManager passwordManager;

    @Override
    public ServerResponse save(PasswordDetail passwordDetail, ApplicationUser applicationUser) {
        return passwordManager.save(passwordDetail, applicationUser);
    }

    @Override
    public ServerResponse changePassword(ApplicationUser applicationUser, ChangePasswordRequest changePasswordRequest) {
        PasswordDetail passwordDetail = new PasswordDetail();
        passwordDetail.setOldPassword(changePasswordRequest.getOldPassword());
        passwordDetail.setPassword(changePasswordRequest.getNewPassword());
        passwordDetail.setAllowOldPassword(false);
        return passwordManager.changePassword(applicationUser, passwordDetail);
    }

    @Override
    public ServerResponse valid(String password) {
        return passwordManager.isValid(password);
    }

    @Override
    public ServerResponse match(PasswordDetail passwordDetail, ApplicationUser applicationUser) {
        return passwordManager.match(passwordDetail, applicationUser);
    }
}
