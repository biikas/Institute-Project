package com.f1soft.campaign.web.users.dto.request;

import com.f1soft.campaign.common.dto.ModelBase;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class ModifyUserRequest extends ModelBase {
    private String mobileNumber;
    @NotEmpty
    private String name;
    private String emailAddress;
    private char active;
}
