package com.f1soft.campaign.web.dto.response;

import com.f1soft.campaign.web.dto.CampaignMode;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @Author Nitesh Poudel
 */
@Getter
@Setter
public class CampaignModeResponse {

    public List<CampaignMode> campaignModes;
}
