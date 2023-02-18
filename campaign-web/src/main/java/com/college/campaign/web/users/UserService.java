package com.college.campaign.web.users;

import com.college.campaign.web.dto.request.StatusRequest;
import com.college.campaign.web.password.dto.ForgotPasswordRequest;
import com.college.campaign.common.dto.ServerResponse;
import com.college.campaign.repository.Util.SearchQueryParameter;
import com.college.campaign.web.users.dto.request.CreateUserRequest;
import com.college.campaign.web.users.dto.request.ModifyUserRequest;

public interface UserService {

    ServerResponse createUser(CreateUserRequest createUserRequest);

    ServerResponse getActiveUsers();

    ServerResponse getUserById(Long applicationUserId);

    ServerResponse modifyUser(ModifyUserRequest modifyUserRequest, Long applicationUserId);

    ServerResponse forgotPassword(ForgotPasswordRequest forgotPasswordRequest);

    ServerResponse searchUser(SearchQueryParameter searchQueryParameter);

    ServerResponse modifyStatus(Long applicationUserId, StatusRequest statusRequest);

    ServerResponse getAllUser();
}
