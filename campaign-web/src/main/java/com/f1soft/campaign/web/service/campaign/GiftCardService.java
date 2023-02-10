package com.f1soft.campaign.web.service.campaign;

import com.f1soft.campaign.common.dto.ServerResponse;
import com.f1soft.campaign.repository.Util.SearchQueryParameter;
import com.f1soft.campaign.web.giftcard.dto.request.GiftCardCreateRequest;
import com.f1soft.campaign.web.giftcard.dto.request.GiftCardModifyRequest;
import com.f1soft.campaign.web.giftcard.dto.request.GiftCardProviderCreateRequest;
import com.f1soft.campaign.web.giftcard.dto.request.UpdateGiftCardStatusRequest;

/**
 * @author <krishna.pandey@f1soft.com>
 */
public interface GiftCardService {

    ServerResponse createGiftCard(GiftCardCreateRequest giftCardCreateRequest);

    ServerResponse createGiftCardProvider(GiftCardProviderCreateRequest giftCardCreateRequest);

    ServerResponse giftCardDetail(Long id);

    ServerResponse modifyGiftCard(GiftCardModifyRequest giftCardModifyRequest);

    ServerResponse updateGiftCardStatus(UpdateGiftCardStatusRequest updateGiftCardStatusRequest);

    ServerResponse delete(Long campaignId);

    ServerResponse searchGiftCard(SearchQueryParameter searchQueryParameter);

    ServerResponse getAllProviders();

    ServerResponse getAllGiftCard();
}
