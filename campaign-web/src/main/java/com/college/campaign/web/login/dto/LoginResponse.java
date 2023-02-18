package com.college.campaign.web.login.dto;


import com.college.campaign.common.dto.BaseResponse;
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
