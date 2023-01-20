package com.nikosera.user.validator;

import com.nikosera.common.exception.EmptyResultException;
import com.nikosera.common.exception.LimitedPrivilegeException;
import com.nikosera.common.exception.ResourceAlreadyExistException;
import com.nikosera.entity.ApplicationUser;
import com.nikosera.entity.UserGroup;
import com.nikosera.entity.UserMenu;
import com.nikosera.repository.repository.core.UserGroupRepository;
import com.nikosera.user.request.AddUserGroupRequest;
import com.nikosera.user.request.UpdateGroupRequest;
import com.nikosera.user.response.dto.UserGroupDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class UserGroupValidator {

    private final UserGroupRepository repository;

    public void validateGroupById(Long id, List<UserGroupDTO> userGroupResponse) {
        userGroupResponse = userGroupResponse.stream().filter((group) -> group.getId() == id).collect(Collectors.toList());
        if (userGroupResponse.size() == 0) {
            throw new EmptyResultException("User group not found based on search parameter");
        }
    }

    public void validateUserGroup(AddUserGroupRequest groupRequest, ApplicationUser applicationUser) {

        if (doesUserGroupAlreadyExist(groupRequest.getName()))
            throw new ResourceAlreadyExistException("Duplicate name : " + groupRequest.getName());

        if (!isGroupPermissionsValid(applicationUser.getUserGroup(), groupRequest.getPermissions()))
            throw new LimitedPrivilegeException("Current user doesn't have requested permission");

    }

    public void validateUpdateGroup(UpdateGroupRequest groupRequest, ApplicationUser applicationUser, Long groupId) {
        if (doesUserGroupAlreadyExist(groupRequest.getName(), groupId))
            throw new ResourceAlreadyExistException("Duplicate name : " + groupRequest.getName());

        if (groupRequest.getPermissions() != null) {
            if (!isGroupPermissionsValid(applicationUser.getUserGroup(), groupRequest.getPermissions()))
                throw new LimitedPrivilegeException("Current user doesn't have requested permission");
        }
    }

    public boolean isGroupPermissionsValid(UserGroup mainGroup, List<Long> groupPermissionRequestList) {
        Map<Long, UserMenu> availableMenus = mainGroup.getPermissions().stream().filter(o -> o.getEnabled() == 'Y').collect(Collectors.toMap(o -> o.getUserMenu().getId(), o -> o.getUserMenu()));
        long count = groupPermissionRequestList.stream().filter(permissionId -> availableMenus.get(permissionId) == null).count();
        return count == 0;
    }


    public boolean doesUserGroupAlreadyExist(String name) {
        return repository.findUserGroupByName(name).isPresent();
    }

    public boolean doesUserGroupAlreadyExist(String name, Long id) {
        return repository.findUserGroupByNameAndIdNot(name, id).isPresent();
    }
}
