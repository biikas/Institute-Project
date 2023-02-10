package com.f1soft.campaign.web.dto;

import com.f1soft.campaign.common.dto.ModelBase;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CampaignServiceResponse extends ModelBase {

    private Long id;
    private String code;
    private String name;
    private String module;
    private char active;
}
