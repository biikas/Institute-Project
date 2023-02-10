package com.f1soft.campaign.web.login.dto;


import com.f1soft.campaign.common.dto.BaseResponse;
import com.f1soft.campaign.web.dto.MenuDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/*
 * @Author Rashim Dhaubanjar
 */
@Getter
@Setter
public class LoginResponse extends BaseResponse {

    private String username;
    private String name;
    private String token;
    private String emailAddress;
    private String type;
    private List<MenuDTO> roles;
}
