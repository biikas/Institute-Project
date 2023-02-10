package com.f1soft.campaign.web.campaign.dto;

import com.f1soft.campaign.common.dto.ModelBase;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Prajwol hada
 */
@Getter
@Setter
public class CampaignServiceDTO extends ModelBase {

    private String code;
    private String name;
    private String module;
}
