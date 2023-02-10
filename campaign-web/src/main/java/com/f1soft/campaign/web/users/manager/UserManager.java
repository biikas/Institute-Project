package com.f1soft.campaign.web.users.manager;

import com.f1soft.campaign.common.constant.MsgConstant;
import com.f1soft.campaign.common.exception.ResourceAlreadyExistException;
import com.f1soft.campaign.common.util.ResponseMsg;
import com.f1soft.campaign.entities.model.ApplicationUser;
import com.f1soft.campaign.repository.ApplicationUserRepository;
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
