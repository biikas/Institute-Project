package com.f1soft.campaign.web.service.campaign;


import com.f1soft.campaign.common.dto.ServerResponse;
import com.f1soft.campaign.repository.Util.SearchQueryParameter;

public interface CampaignService {

    ServerResponse getAllCampaign();

    ServerResponse campaignDetail(Long id);

    ServerResponse searchCampaign(SearchQueryParameter searchQueryParameter);

    ServerResponse getAllOfferMode();

    ServerResponse getAllServices();

    ServerResponse getAllCustomerProfiles();

    ServerResponse getCampaignModes();

    ServerResponse campaignRedeemDetail();

    ServerResponse campaignCreatorProfile(Long campaignId);
}
