package com.college.campaign.web.campaign.service;

import com.college.campaign.common.dto.ServerResponse;
import com.college.campaign.web.campaign.dto.request.campaign.CreateCampaignRequest;
import com.college.campaign.web.campaign.dto.request.campaign.ModifyCampaignRequest;
import com.college.campaign.web.campaign.dto.request.campaign.UpdateCampaignStatusRequest;

/**
 * @author Prajwol hada
 */
public interface CampaignCrudService {

    ServerResponse createCampaign(CreateCampaignRequest createCampaignRequest);

    ServerResponse modifyCampaign(ModifyCampaignRequest modifyCampaignRequest);

    ServerResponse updateCampaignStatus(UpdateCampaignStatusRequest updateCampaignStatusRequest);

    ServerResponse delete(Long campaignId);

}
