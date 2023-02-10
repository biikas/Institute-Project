package com.f1soft.campaign.client.dto;

import com.f1soft.campaign.common.dto.ModelBase;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmailDynamicParamDTO extends ModelBase {

    private String paramKey;
    private String paramValue;

}
