package com.college.campaign.web.dto;

import com.college.campaign.common.dto.ModelBase;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Profile extends ModelBase {
    private String username;
    private String name;
    private String mobileNumber;
    private String email;
}
