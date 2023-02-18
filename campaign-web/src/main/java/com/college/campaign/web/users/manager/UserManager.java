package com.college.campaign.web.users.manager;

import com.college.campaign.common.constant.MsgConstant;
import com.college.campaign.common.exception.ResourceAlreadyExistException;
import com.college.campaign.common.util.ResponseMsg;
import com.college.campaign.entities.model.ApplicationUser;
import com.college.campaign.repository.ApplicationUserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class UserManager {

    @Autowired
    private ApplicationUserRepository applicationUserRepository;

    public boolean checkIfUsernameExist(String username) {
        List<ApplicationUser> applicationUsers = applicationUserRepository.checkUserNameExist(username);
        if (!applicationUsers.isEmpty()) {
            throw new ResourceAlreadyExistException(ResponseMsg.failureResponse(MsgConstant.USERNAME_ALREADY_EXIST));
        }
        return true;
    }
}
