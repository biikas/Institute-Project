package com.nikosera.user.mapper;

import com.nikosera.common.constant.ApiConstant;
import com.nikosera.entity.UserGroup;
import com.nikosera.user.response.ApplicationUserDetail;
import com.nikosera.user.response.dto.UserGroupDTO;
import com.nikosera.user.response.dto.UserMenuDTO;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class UserGroupMapper {

    public static UserGroupDTO createUserGroupResponse(UserGroup userGroup, String type) {
        UserGroupDTO userGroupDetail = new UserGroupDTO();
        userGroupDetail.setDescription(userGroup.getDescription());
        userGroupDetail.setName(userGroup.getName());
        userGroupDetail.setActive(userGroup.getActive());
        userGroupDetail.setParentId(userGroup.getParent() != null ? new UserGroupDTO(userGroup.getParent().getId(), userGroup.getParent().getName(), null) : null);
        userGroupDetail.setId(userGroup.getId());
        userGroupDetail.setCreatedOn(userGroup.getCreatedDate());
        userGroupDetail.setCreatedBy(new ApplicationUserDetail(userGroup.getCreatedBy().getId(), userGroup.getCreatedBy().getName()));
        if (userGroup.getLastModifiedBy() != null) {
            userGroupDetail.setModifiedOn(userGroup.getLastModifiedDate());
            userGroupDetail.setModifiedBy(new ApplicationUserDetail(userGroup.getLastModifiedBy().getId(), userGroup.getLastModifiedBy().getName()));
        }
        if (type.equals(ApiConstant.GET_BY_ID)) {
            if (userGroup.getPermissions() != null) {
                List<UserMenuDTO> userMenuList = userGroup.getPermissions().stream().filter(permission -> permission.getUserMenu().getParentId() == null)
                        .filter(permission -> permission.getEnabled() == 'Y')
                        .map(permission ->
                                UserMenuMapper.mapToUserMenu(permission.getUserMenu())
                        ).collect(Collectors.toList());

                userGroupDetail.setPermissions(userMenuList.stream().map(userMenu -> {
                    userMenu.setChildPermission(userGroup.getPermissions().stream()
                            .filter(permission -> permission.getUserMenu().getParentId() == userMenu.getId())
                            .filter(permission -> permission.getEnabled() == 'Y')
                            .map(permission -> UserMenuMapper.mapToUserMenu(permission.getUserMenu())
                            ).collect(Collectors.toList()));

                    userMenu.getChildPermission().stream().map(childMenu -> {
                        childMenu.setChildPermission(userGroup.getPermissions().stream()
                                .filter(permission -> permission.getUserMenu().getParentId() == childMenu.getId())
                                .filter(permission -> permission.getEnabled() == 'Y')
                                .map(permission -> UserMenuMapper.mapToUserMenu(permission.getUserMenu())
                                ).collect(Collectors.toList()));
                        return childMenu;
                    }).collect(Collectors.toList());

                    return userMenu;
                }).collect(Collectors.toList()));
            }
        }
        return userGroupDetail;
    }
}
