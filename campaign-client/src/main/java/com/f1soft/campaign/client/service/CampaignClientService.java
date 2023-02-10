package com.f1soft.campaign.client.service;

import com.f1soft.campaign.client.dto.request.GiftCardFetchRequest;
import com.f1soft.campaign.client.dto.request.OfferFetchRequest;
import com.f1soft.campaign.common.dto.ServerResponse;

/**
 * @author Prajwol hada
 */
public interface CampaignClientService {

    ServerResponse campaignList();

    ServerResponse campaignListByUser(OfferFetchRequest offerFetchRequest);

    ServerResponse userGiftCards(GiftCardFetchRequest giftCardFetchRequest);
}
