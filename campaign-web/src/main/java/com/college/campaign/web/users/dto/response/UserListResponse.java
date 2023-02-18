package com.college.campaign.web.users.dto.response;

import com.college.campaign.common.dto.ModelBase;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserListResponse extends ModelBase {
    List<UserResponse> userList;
}
