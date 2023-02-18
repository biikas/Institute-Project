package com.college.campaign.web.password;


import com.college.campaign.web.password.dto.ChangePasswordRequest;
import com.college.campaign.web.password.dto.PasswordDetail;
import com.college.campaign.common.dto.ServerResponse;
import com.college.campaign.entities.model.ApplicationUser;

/**
 * @author Rashim Dhaubanjar
 */

public interface PasswordService {

    ServerResponse save(PasswordDetail passwordDetail, ApplicationUser applicationUser);

    ServerResponse changePassword(ApplicationUser applicationUser, ChangePasswordRequest changePasswordRequest);

    ServerResponse valid(String password);

    ServerResponse match(PasswordDetail passwordDetail, ApplicationUser applicationUser);

}
