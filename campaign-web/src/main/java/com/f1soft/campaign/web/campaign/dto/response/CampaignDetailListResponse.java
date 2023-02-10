package com.f1soft.campaign.web.campaign.dto.response;

import com.f1soft.campaign.common.dto.ModelBase;
import com.f1soft.campaign.web.campaign.dto.CampaignDetailDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author Shreetika Panta
 */

@Getter
@Setter
public class CampaignDetailListResponse extends ModelBase {

    private List<CampaignDetailDTO> campaignDetails;
}
