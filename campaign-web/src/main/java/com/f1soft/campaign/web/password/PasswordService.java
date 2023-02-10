package com.f1soft.campaign.web.password;


import com.f1soft.campaign.common.dto.ServerResponse;
import com.f1soft.campaign.entities.model.ApplicationUser;
import com.f1soft.campaign.web.password.dto.ChangePasswordRequest;
import com.f1soft.campaign.web.password.dto.PasswordDetail;

/**
 * @author Rashim Dhaubanjar
 */

public interface PasswordService {

    ServerResponse save(PasswordDetail passwordDetail, ApplicationUser applicationUser);

    ServerResponse changePassword(ApplicationUser applicationUser, ChangePasswordRequest changePasswordRequest);

    ServerResponse valid(String password);

    ServerResponse match(PasswordDetail passwordDetail, ApplicationUser applicationUser);

}
