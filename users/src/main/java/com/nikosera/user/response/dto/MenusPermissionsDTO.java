package com.nikosera.user.response.dto;

import com.nikosera.common.dto.ModelBase;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author Narayan Joshi
 * @email narenzoshi@gmail.com
 */
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MenusPermissionsDTO extends ModelBase {

    private List<MenuDTO> menus;
    private List<String> roles;
    private List<String> permissions;
}
