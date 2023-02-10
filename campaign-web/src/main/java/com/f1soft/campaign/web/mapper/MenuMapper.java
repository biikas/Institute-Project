package com.f1soft.campaign.web.mapper;

import com.f1soft.campaign.entities.model.AdminMenu;
import com.f1soft.campaign.web.dto.MenuDTO;

/**
 * @Author Nitesh Poudel
 */
public class MenuMapper {

    private MenuMapper() {

    }

    public static MenuDTO convertToMenuDTO(AdminMenu adminMenu) {
        MenuDTO menuDTO = new MenuDTO();
        menuDTO.setId(adminMenu.getId());
        menuDTO.setCode(adminMenu.getCode());
        menuDTO.setName(adminMenu.getName());
        menuDTO.setDescription(adminMenu.getDescription());
        menuDTO.setAdminMenuGroupName(adminMenu.getAdminMenuGroup().getName());
        return menuDTO;
    }

}
