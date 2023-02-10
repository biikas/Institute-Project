package com.f1soft.campaign.web.token;

import com.f1soft.campaign.common.dto.ModelBase;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TokenDTO extends ModelBase {

    private String token;
    private String prefix;
    private String username;
}
