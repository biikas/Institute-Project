package com.f1soft.campaign.web.users;

import com.f1soft.campaign.common.dto.ServerResponse;
import com.f1soft.campaign.repository.Util.SearchQueryParameter;
import com.f1soft.campaign.web.dto.request.StatusRequest;
import com.f1soft.campaign.web.password.dto.ForgotPasswordRequest;
import com.f1soft.campaign.web.users.dto.request.CreateUserRequest;
import com.f1soft.campaign.web.users.dto.request.ModifyUserRequest;

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
