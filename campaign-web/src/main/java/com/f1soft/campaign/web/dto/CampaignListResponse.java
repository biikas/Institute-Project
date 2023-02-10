package com.f1soft.campaign.web.dto;

import com.f1soft.campaign.common.dto.ModelBase;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CampaignListResponse extends ModelBase {

    List<CampaignResponse> campaignList;
}
