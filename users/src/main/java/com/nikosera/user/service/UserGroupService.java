package com.nikosera.user.service;

import com.nikosera.common.dto.GenericResponse;
import com.nikosera.user.request.AddUserGroupRequest;
import com.nikosera.user.request.UpdateGroupRequest;
import com.nikosera.user.request.UserGroupSearchRequest;

/**
 * @author Narayan Joshi
 * @email narenzoshi@gmail.com
 */
public interface UserGroupService {

    GenericResponse getAllUserGroups();

    GenericResponse getGroup(Long id);

    GenericResponse saveUserGroup(AddUserGroupRequest request);

    GenericResponse modifyUserGroup(UpdateGroupRequest request, Long groupId);

    GenericResponse searchUserGroup(UserGroupSearchRequest request);
}
