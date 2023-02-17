package com.f1soft.campaign.web.users.mapper;

import com.f1soft.campaign.entities.model.ApplicationUser;
import com.f1soft.campaign.web.config.provider.LoginProvider;
import com.f1soft.campaign.web.constant.Constant;
import com.f1soft.campaign.web.users.dto.request.CreateUserRequest;
import com.f1soft.campaign.web.users.dto.request.ModifyUserRequest;
import com.f1soft.campaign.web.users.dto.response.UserResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Date;

@Slf4j
@Component
public class UserMapper {

    public UserMapper() {
    }

    public static UserResponse convertToUserResponse(ApplicationUser applicationUser) {
        UserResponse userResponse = new UserResponse();
        userResponse.setId(applicationUser.getId());
        userResponse.setUserName(applicationUser.getUsername());
        userResponse.setName(applicationUser.getName());
        userResponse.setMobileNumber(applicationUser.getMobileNumber());
        userResponse.setEmailAddress(applicationUser.getEmailAddress());
        userResponse.setActive(applicationUser.getActive());
        return userResponse;
    }


    public ApplicationUser convertToCreateUser(CreateUserRequest createUserRequest) {
        ApplicationUser applicationUser = new ApplicationUser();
        applicationUser.setUsername(createUserRequest.getUserName().trim());
        applicationUser.setMobileNumber(createUserRequest.getMobileNumber());
        applicationUser.setName(createUserRequest.getName().trim());
        applicationUser.setActive('Y');
        applicationUser.setCreatedDate(new Date());
        applicationUser.setCreatedBy(LoginProvider.getApplicationUser());
        applicationUser.setAdminTypeName(Constant.AdminType.SUPER_ADMIN);
        if (createUserRequest.getEmailAddress() != null) {
            applicationUser.setEmailAddress(createUserRequest.getEmailAddress().trim());
        }
        return applicationUser;
    }

    public ApplicationUser convertToModifyUser(ApplicationUser applicationUser, ModifyUserRequest modifyUserRequest) {
        applicationUser.setMobileNumber(modifyUserRequest.getMobileNumber());
        applicationUser.setName(modifyUserRequest.getName());
        applicationUser.setEmailAddress(modifyUserRequest.getEmailAddress());
        applicationUser.setLastModifiedDate(new Date());
        applicationUser.setLastModifiedBy(LoginProvider.getApplicationUser());
        applicationUser.setActive(modifyUserRequest.getActive());
        return applicationUser;
    }
}
