package com.f1soft.campaign.web.dto;

import com.f1soft.campaign.common.dto.ModelBase;
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
