package com.f1soft.campaign.web.mapper;

import com.f1soft.campaign.common.util.DateFormat;
import com.f1soft.campaign.common.util.DateFormatter;
import com.f1soft.campaign.entities.model.ApplicationUser;
import com.f1soft.campaign.entities.model.GiftCard;
import com.f1soft.campaign.entities.model.GiftCardProvider;
import com.f1soft.campaign.web.dto.response.GiftCardProviderDetail;
import com.f1soft.campaign.web.dto.response.GiftCardResponse;
import com.f1soft.campaign.web.giftcard.dto.request.GiftCardCreateRequest;
import com.f1soft.campaign.web.giftcard.dto.request.GiftCardModifyRequest;
import com.f1soft.campaign.web.giftcard.dto.request.GiftCardProviderCreateRequest;

import java.util.Date;

/**
 * @author <krishna.pandey@f1soft.com>
 */
public class GiftCardMapper {

    private GiftCardMapper () {}

    public static GiftCardResponse convertToGiftCardResponse(GiftCard giftCard) {

        GiftCardResponse giftCardResponse = new GiftCardResponse();
        giftCardResponse.setId(giftCard.getId());
        giftCardResponse.setActive(giftCard.getActive());
        giftCardResponse.setName(giftCard.getName());
        giftCardResponse.setAmount(String.valueOf(giftCard.getAmount()));
        giftCardResponse.setCode(giftCard.getCode());
        giftCardResponse.setGiftCardProvider(
                convertToGiftCardProviderResponse(giftCard.getGiftCardProvider()));
        giftCardResponse.setExpiryDate(DateFormatter.convertToString(giftCard.getExpiryDate(), DateFormat.DATE_FORMAT));
        return giftCardResponse;
    }

    public static GiftCardProviderDetail convertToGiftCardProviderResponse(GiftCardProvider giftCardProvider) {

        GiftCardProviderDetail giftCardProviderDetail = new GiftCardProviderDetail();
        giftCardProviderDetail.setId(giftCardProvider.getId());
        giftCardProviderDetail.setActive(giftCardProvider.getActive());
        giftCardProviderDetail.setName(giftCardProvider.getName());
        giftCardProviderDetail.setCode(giftCardProvider.getCode());
        return giftCardProviderDetail;
    }

    public static GiftCardProvider convertToGiftCardProvider(GiftCardProviderCreateRequest giftCardProvider) {

        GiftCardProvider giftCardProviderDetail = new GiftCardProvider();
        giftCardProviderDetail.setActive('Y');
        giftCardProviderDetail.setName(giftCardProvider.getName());
        giftCardProviderDetail.setCode(giftCardProvider.getCode());
        return giftCardProviderDetail;
    }


    public static GiftCard mapToGiftCardDetail(GiftCardCreateRequest giftCardCreateRequest, ApplicationUser applicationUser,
                                               GiftCardProvider giftCardProvider) {
        GiftCard giftCard = new GiftCard();
        giftCard.setActive('Y');
        giftCard.setAmount(giftCardCreateRequest.getAmount() != null ? Double.valueOf(giftCardCreateRequest.getAmount()) : null);
        giftCard.setName(giftCardCreateRequest.getName());
        giftCard.setCode(giftCardCreateRequest.getCode());
        giftCard.setGiftCardProvider(giftCardProvider);
        giftCard.setCreatedBy(applicationUser);
        giftCard.setCreatedDate(new Date());
        giftCard.setExpiryDate(DateFormatter.convertToDate(giftCardCreateRequest.getExpiryDate()));
        return giftCard;
    }

    public static GiftCard giftCardForModification(GiftCardModifyRequest giftCardModifyRequest, GiftCard giftCard,
                                                   ApplicationUser applicationUser, GiftCardProvider giftCardProvider) {
        giftCard.setName(giftCardModifyRequest.getName());
        giftCard.setCode(giftCardModifyRequest.getCode());
        giftCard.setAmount(giftCardModifyRequest.getAmount() != null ? Double.valueOf(giftCardModifyRequest.getAmount()) : null);
        giftCard.setGiftCardProvider(giftCardProvider);
        giftCard.setModifiedBy(applicationUser);
        giftCard.setModifiedDate(new Date());
        giftCard.setActive(giftCardModifyRequest.getActive());
        giftCard.setExpiryDate(DateFormatter.convertToDate(giftCardModifyRequest.getExpiryDate()));
        return giftCard;
    }
}
