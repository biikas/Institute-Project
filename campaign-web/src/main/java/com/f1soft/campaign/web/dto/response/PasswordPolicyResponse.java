package com.f1soft.campaign.web.dto.response;

import com.f1soft.campaign.common.dto.ModelBase;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PasswordPolicyResponse extends ModelBase {

    private List<String> passwordPolicy;
    private Integer minLength;
    private Integer maxLength;
}
