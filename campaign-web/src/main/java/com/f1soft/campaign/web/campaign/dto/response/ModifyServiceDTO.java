package com.f1soft.campaign.web.campaign.dto.response;

import com.f1soft.campaign.common.dto.ModelBase;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ModifyServiceDTO extends ModelBase {

    private String code;
    private String name;
    private String module;
}
