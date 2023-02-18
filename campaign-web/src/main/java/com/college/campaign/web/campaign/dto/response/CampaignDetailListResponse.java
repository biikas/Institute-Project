package com.college.campaign.web.campaign.dto.response;

import com.college.campaign.web.campaign.dto.CampaignDetailDTO;
import com.college.campaign.common.dto.ModelBase;
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
