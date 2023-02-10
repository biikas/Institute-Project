package com.f1soft.campaign.client.mapper;

import com.f1soft.campaign.client.dto.CampaignOfferDTO;
import com.f1soft.campaign.common.util.DateFormat;
import com.f1soft.campaign.common.util.DateFormatter;
import com.f1soft.campaign.entities.model.Campaign;

/**
 * @author Prajwol hada
 */
public class CampaignMapper {

    private CampaignMapper() {
    }

    public static CampaignOfferDTO convertToCampaignResponse(Campaign campaign, String imagePath) {
        CampaignOfferDTO campaignResponse = new CampaignOfferDTO();

        campaignResponse.setId(campaign.getId());
        campaignResponse.setTitle(campaign.getTitle());
        campaignResponse.setShortDescription(campaign.getShortDescription());
        campaignResponse.setDescription(campaign.getDescription());
        campaignResponse.setPromoCode(campaign.getPromoCode());
        campaignResponse.setStartDate(DateFormatter.convertToString(campaign.getStartDate(), DateFormat.DATE_FORMAT));
        campaignResponse.setEndDate(DateFormatter.convertToString(campaign.getEndDate(), DateFormat.DATE_FORMAT));
        campaignResponse.setImagePath(imagePath + "/" + campaign.getImage());
        campaignResponse.setPolicy(campaign.getPolicy());
        campaignResponse.setOfferLink(campaign.getOfferLink());

        return campaignResponse;
    }
}
