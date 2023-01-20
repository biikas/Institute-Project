package com.nikosera.user.mapper;

import com.nikosera.common.constant.AuthorizationGrant;
import com.nikosera.common.constant.NavTypeConstant;
import com.nikosera.entity.ApplicationUser;
import com.nikosera.entity.UserGroup;
import com.nikosera.user.response.dto.MenuDTO;
import com.nikosera.user.response.dto.MenusPermissionsDTO;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Narayan Joshi
 * @email narenzoshi@gmail.com
 */
public class MenuDetailMapper {

    public static MenusPermissionsDTO mapMenusPermissionsDTO(ApplicationUser applicationUser) {
        UserGroup userGroup = applicationUser.getUserGroup();
        MenusPermissionsDTO menusPermissions = new MenusPermissionsDTO();

        List<MenuDTO> menus = new ArrayList<>();
        List<String> roles = new ArrayList<>();
        List<String> permissions = new ArrayList<>();

        if (userGroup.getPermissions() != null && !userGroup.getPermissions().isEmpty()) {

            // Filter active case and Map to DTO
            List<MenuDTO> menuList = userGroup
                    .getPermissions()
                    .stream()
                    .filter(userGroupPermission -> userGroupPermission.getEnabled() == 'Y'
                            && userGroupPermission.getUserGroup().getActive() == 'Y'
                            && userGroupPermission.getUserMenu().getActive() == 'Y')
                    .map(userGroupPermission -> {
                        return UserMenuMapper.mapToMenuDTO(userGroupPermission.getUserMenu());
                    })
                    .collect(Collectors.toList());

            // Map permissions
            permissions = menuList
                    .stream()
                    .map(MenuDTO::getAction)
                    .collect(Collectors.toList());

            // Add role
            roles.add(AuthorizationGrant.ROLE_PREFIX + userGroup.getName());

            // Menus map
            Map<Long, MenuDTO> menuMap = menuList
                    .stream()
                    .filter((menu) -> (menu.getNavType() != null && menu.getNavType().equalsIgnoreCase(NavTypeConstant.SIDE)))
                    .collect(Collectors.toMap(MenuDTO::getId, menu -> menu));

            // Set Menus with hierarchical relationship
            menuMap.forEach((menuId, menu) -> {
                if (menu.getParentId() == null) { // menus having parent id null are top or base level menus
                    menus.add(menu); // adds to the top or base level
                } else {
                    MenuDTO menuFound = menuMap.get(menu.getParentId()); // finds its parent menu
                    if (menuFound != null) {
                        menuFound.addChildren(menu); // adds itself inside the parent object
                    } else { // handles the case that menus is found though menu with parent id doesnt exists; so we add it to the top or base level
                        menus.add(menu);
                    }
                }
            });

        }

        // Sort menus based on sortOrder
        sortMenus(menus);

        menusPermissions.setMenus(menus);
        menusPermissions.setRoles(roles);
        menusPermissions.setPermissions(permissions);

        return menusPermissions;
    }

    public static void sortMenus(List<MenuDTO> menuList) {
        menuList.sort(Comparator.comparing(MenuDTO::getSortOrder));
        menuList.forEach(menu -> {
            List<MenuDTO> menuChildren = menu.getChildPermission();
            if (menu.getChildPermission().size() > 0) {
                sortMenus(menuChildren);
            }
        });
    }
}