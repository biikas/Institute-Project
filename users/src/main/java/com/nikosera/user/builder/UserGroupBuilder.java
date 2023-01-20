package com.nikosera.user.builder;

import com.nikosera.entity.ApplicationUser;
import com.nikosera.entity.UserGroup;
import com.nikosera.entity.UserGroupPermission;
import com.nikosera.entity.UserMenu;
import com.nikosera.repository.repository.core.UserGroupPermissionRepository;
import com.nikosera.user.request.AddUserGroupRequest;
import com.nikosera.user.request.UpdateGroupRequest;

import java.util.List;
import java.util.stream.Collectors;

import static com.nikosera.notification.constant.StringConstants.NO;
import static com.nikosera.notification.constant.StringConstants.YES;

/**
 * @author Narayan Joshi
 * @email narenzoshi@gmail.com
 */
public class UserGroupBuilder {

    public static UserGroup mapToGroup(AddUserGroupRequest request, ApplicationUser loginUser) {
        UserGroup userGroup = new UserGroup();
        userGroup.setDescription(request.getDescription());
        userGroup.setName(request.getName());
        userGroup.setActive(YES);
        userGroup.setCreatedBy(loginUser);
        userGroup.setParent(loginUser.getUserGroup());

        if (request.getPermissions() != null) {
            List<UserGroupPermission> groupPermissions = mapPermission(request.getPermissions(), userGroup);
            userGroup.setPermissions(groupPermissions);
        }
        return userGroup;
    }

    public static UserGroup mapToUpdateGroup(UpdateGroupRequest request, UserGroup userGroup, ApplicationUser loginUser,
                                             UserGroupPermissionRepository permissionRepository) {
        userGroup.setDescription(request.getDescription() != null ? request.getDescription() : userGroup.getDescription());
        userGroup.setName(request.getName() != null ? request.getName() : userGroup.getName());
        userGroup.setActive(request.getActive() != null ? request.getActive() : userGroup.getActive());

        if (request.getPermissions() != null) {
            List<UserGroupPermission> groupPermission = permissionRepository.findAllByUserGroupAndPermission(userGroup.getId());
            groupPermission.stream().forEach(permission -> {
                permission.setEnabled(NO);
                permissionRepository.save(permission);
            });

            List<UserGroupPermission> groupPermissions = mapPermission(request.getPermissions(), userGroup);
            userGroup.setPermissions(groupPermissions);
        }

        return userGroup;
    }

    private static List<UserGroupPermission> mapPermission(List<Long> permissions, UserGroup userGroup) {
        List<UserGroupPermission> groupPermissions = permissions.stream().map((groupServiceDetail) -> {
            UserGroupPermission groupService = new UserGroupPermission();
            groupService.setEnabled(YES);
            groupService.setUserGroup(userGroup);
            groupService.setUserMenu(new UserMenu(groupServiceDetail));
            return groupService;
        }).collect(Collectors.toList());

        return groupPermissions;
    }
}
