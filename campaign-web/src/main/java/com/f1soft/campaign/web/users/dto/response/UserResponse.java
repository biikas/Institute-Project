package com.f1soft.campaign.web.users.dto.response;

import com.f1soft.campaign.common.dto.ModelBase;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponse extends ModelBase {
    
    private Long id;
    private String userName;
    private String mobileNumber;
    private String name;
    private String emailAddress;
    private char active;
}
