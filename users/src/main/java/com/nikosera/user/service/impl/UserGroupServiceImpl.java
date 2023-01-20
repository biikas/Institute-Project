package com.nikosera.user.service.impl;

import com.nikosera.common.aop.MethodLogging;
import com.nikosera.common.builder.MsgBuilder;
import com.nikosera.common.builder.ResponseBuilder;
import com.nikosera.common.constant.ApiConstant;
import com.nikosera.common.constant.MsgConstant;
import com.nikosera.common.dto.GenericResponse;
import com.nikosera.common.util.ApplicationUtil;
import com.nikosera.common.util.OptionalList;
import com.nikosera.entity.ApplicationUser;
import com.nikosera.entity.UserGroup;
import com.nikosera.entity.UserGroupPermission;
import com.nikosera.repository.repository.core.UserGroupPermissionRepository;
import com.nikosera.repository.repository.core.UserGroupRepository;
import com.nikosera.user.builder.UserGroupBuilder;
import com.nikosera.user.mapper.UserGroupMapper;
import com.nikosera.user.request.AddUserGroupRequest;
import com.nikosera.user.request.UpdateGroupRequest;
import com.nikosera.user.request.UserGroupSearchRequest;
import com.nikosera.user.response.dto.UserGroupDTO;
import com.nikosera.user.service.UserGroupService;
import com.nikosera.user.util.UserGroupUtil;
import com.nikosera.user.validator.UserGroupValidator;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Narayan Joshi
 * @email narenzoshi@gmail.com
 */
@Slf4j
@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserGroupServiceImpl implements UserGroupService {

    private final UserGroupRepository repository;
    private final UserGroupPermissionRepository permissionRepository;
    private final UserGroupValidator groupValidator;
    private final ApplicationUtil applicationUtil;
    private final UserGroupUtil userGroupUtil;

    @MethodLogging
    @Override
    public GenericResponse getAllUserGroups() {
        List<UserGroupDTO> response = OptionalList
                .of(userGroupUtil.getUserGroups(applicationUtil.loggedInUser()))
                .orElseThrow(MsgBuilder.emptyDataList(MsgConstant.Model.USER_GROUP));

        return ResponseBuilder
                .buildSuccessMessage(response, MsgBuilder.successList(MsgConstant.Model.USER_GROUP));
    }

    @MethodLogging
    @Override
    public GenericResponse getGroup(Long userId) {
        UserGroup userGroup = repository
                .findById(userId)
                .orElseThrow(MsgBuilder.doesntExist(MsgConstant.Model.USER_GROUP));

        groupValidator.validateGroupById(userId, userGroupUtil.getUserGroups(applicationUtil.loggedInUser()));

        UserGroupDTO response = UserGroupMapper.createUserGroupResponse(userGroup, ApiConstant.GET_BY_ID);

        return ResponseBuilder
                .buildSuccessMessage(response, MsgBuilder.successSingle(MsgConstant.Model.USER_GROUP));
    }

    @MethodLogging
    @Override
    public GenericResponse saveUserGroup(AddUserGroupRequest request) {
        ApplicationUser loginUser = applicationUtil.loggedInUser();

        groupValidator.validateUserGroup(request, loginUser);

        UserGroup userGroup = UserGroupBuilder.mapToGroup(request, loginUser);

        saveUserGroup(userGroup);

        saveGroupPermission(userGroup.getPermissions());

        return ResponseBuilder
                .buildSuccessMessage(MsgBuilder.successSave(MsgConstant.Model.USER_GROUP));
    }

    @MethodLogging
    @Override
    public GenericResponse modifyUserGroup(UpdateGroupRequest request, Long groupId) {
        groupValidator.validateGroupById(groupId, userGroupUtil.getUserGroups(applicationUtil.loggedInUser()));
        UserGroup userGroup = repository
                .findById(groupId)
                .orElseThrow(MsgBuilder.doesntExist(MsgConstant.Model.USER_GROUP));

        ApplicationUser loginUser = applicationUtil.loggedInUser();

        groupValidator.validateUpdateGroup(request, loginUser, groupId);

        UserGroupBuilder.mapToUpdateGroup(request, userGroup, loginUser, permissionRepository);

        saveUserGroup(userGroup);

        return ResponseBuilder
                .buildSuccessMessage(MsgBuilder.successUpdate(MsgConstant.Model.USER_GROUP));
    }

    @MethodLogging
    @Override
    public GenericResponse searchUserGroup(UserGroupSearchRequest request) {
        List<UserGroupDTO> response = userGroupUtil.
                searchUserGroup(applicationUtil.loggedInUser(), request);

        return ResponseBuilder
                .buildSuccessMessage(response, MsgBuilder.successList(MsgConstant.Model.USER_GROUP));
    }

    private void saveUserGroup(UserGroup userGroup){
        repository.save(userGroup);
    }

    private void saveGroupPermission(List<UserGroupPermission> userGroupPermissions){
        permissionRepository.saveAll(userGroupPermissions);
    }


}
