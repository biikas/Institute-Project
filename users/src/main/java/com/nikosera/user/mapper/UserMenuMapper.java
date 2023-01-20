package com.nikosera.user.mapper;

import com.nikosera.common.util.ObjectMapper;
import com.nikosera.entity.UserMenu;
import com.nikosera.user.response.dto.MenuDTO;
import com.nikosera.user.response.dto.UserMenuDTO;

/**
 * @author Narayan Joshi
 * <narenzoshi@gmail.com>
 */
public class UserMenuMapper {

    public static UserMenuDTO mapToUserMenu(UserMenu menu) {
        UserMenuDTO userMenuDTO = new UserMenuDTO();
        userMenuDTO.setId(menu.getId());
        userMenuDTO.setActive(menu.getActive());
        userMenuDTO.setName(menu.getName());
        return userMenuDTO;
    }

    public static MenuDTO mapToMenuDTO(UserMenu menu) {
        return ObjectMapper.map(menu, MenuDTO.class);
    }
}
