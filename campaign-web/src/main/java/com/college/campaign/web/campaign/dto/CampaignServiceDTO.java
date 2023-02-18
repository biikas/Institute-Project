package com.college.campaign.web.campaign.dto;

import com.college.campaign.common.dto.ModelBase;
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
