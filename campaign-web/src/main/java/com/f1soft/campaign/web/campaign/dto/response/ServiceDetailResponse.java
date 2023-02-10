package com.f1soft.campaign.web.campaign.dto.response;

import com.f1soft.campaign.common.dto.ModelBase;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ServiceDetailResponse extends ModelBase {

    private Long id;
    private String code;
    private String name;
    private String module;
    private char active;
}
