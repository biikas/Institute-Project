package com.f1soft.campaign.client.mapper;

import com.f1soft.campaign.client.dto.GiftCardDTO;
import com.f1soft.campaign.client.dto.GiftCardProviderDTO;
import com.f1soft.campaign.common.util.DateFormatter;
import com.f1soft.campaign.entities.model.*;

/**
 * @Author Nitesh Poudel
 */
public class GiftCardUserMapper {

    private GiftCardUserMapper() {

    }

    public static GiftCardEligibleUser mapToGiftCardEligibleUser(Campaign campaign, OfferTransaction offerTransaction,
                                                                 GiftCard giftCard, String mobileNumber) {

        GiftCardEligibleUser giftCardEligibleUser = new GiftCardEligibleUser();

        giftCardEligibleUser.setCampaign(campaign);
        giftCardEligibleUser.setRedeemed('N');
        giftCardEligibleUser.setOfferTransaction(offerTransaction);
        giftCardEligibleUser.setGiftCard(giftCard);
        giftCardEligibleUser.setUsername(mobileNumber);

        return giftCardEligibleUser;
    }

    public static GiftCardDTO mapToGiftCardDTO(GiftCard giftCard) {

        GiftCardDTO giftCardDTO = new GiftCardDTO();

        giftCardDTO.setId(giftCard.getId());
        giftCardDTO.setActive(giftCard.getActive());
        giftCardDTO.setCode(giftCard.getCode());
        giftCardDTO.setName(giftCard.getName());
        giftCardDTO.setAmount(giftCard.getAmount());
        giftCardDTO.setExpiryDate(DateFormatter.convertToString(giftCard.getExpiryDate()));
        giftCardDTO.setGiftCardProvider(mapToGiftCardProvider(giftCard.getGiftCardProvider()));

        return giftCardDTO;
    }

    public static GiftCardProviderDTO mapToGiftCardProvider(GiftCardProvider giftCardProvider) {

        GiftCardProviderDTO giftCardProviderDTO = new GiftCardProviderDTO();

        giftCardProviderDTO.setActive(giftCardProvider.getActive());
        giftCardProviderDTO.setId(giftCardProvider.getId());
        giftCardProviderDTO.setCode(giftCardProvider.getCode());
        giftCardProviderDTO.setName(giftCardProvider.getName());

        return giftCardProviderDTO;
    }
}
