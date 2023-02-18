package com.college.campaign.web.login.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Bikash Shah
 */
@Getter
@Setter
public class MenuDTO {
    private Long id;
    private String name;
    private String description;
    private String code;
    private String adminMenuGroupName;
}
