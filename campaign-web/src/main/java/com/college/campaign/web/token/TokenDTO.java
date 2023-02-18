package com.college.campaign.web.token;

import com.college.campaign.common.dto.ModelBase;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TokenDTO extends ModelBase {

    private String token;
    private String prefix;
    private String username;
}
