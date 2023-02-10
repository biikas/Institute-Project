package com.f1soft.campaign.web.mapper;

import com.f1soft.campaign.common.cbs.dto.CustomRedeemDTO;
import com.f1soft.campaign.common.enums.OfferTransactionStatusEnum;
import com.f1soft.campaign.entities.model.*;

import java.util.Date;

/**
 * @author Shreetika Panta
 */
public class CustomRedeemMapper {

    private CustomRedeemMapper() {
    }

    public static OfferTransaction convertToSaveOfferTransaction(Campaign campaign, Channel channel, CustomRedeemDTO customRedeemDTO, CampaignOffer campaignOffer) {
        OfferTransaction offerTransaction = new OfferTransaction();
        offerTransaction.setAccountNumber(customRedeemDTO.getAccountNumber());
        offerTransaction.setMobileNumber(customRedeemDTO.getMobileNumber());
        offerTransaction.setAmount(Double.parseDouble(campaignOffer.getValue()));
        offerTransaction.setTransactionAmount(Double.parseDouble(campaignOffer.getValue()));
        offerTransaction.setCampaignOffer(campaignOffer);
        offerTransaction.setPromoCode(campaign.getPromoCode());
        offerTransaction.setRecordedDate(new Date());
        offerTransaction.setCampaign(campaign);
        offerTransaction.setStatus("ACTIVE");
        offerTransaction.setTransactionStatus(OfferTransactionStatusEnum.PENDING.name());
        offerTransaction.setClaimDate(new Date());
        offerTransaction.setChannel(channel);

        return offerTransaction;
    }

    public static CustomCampaignEligibleUser convertToSaveCustomCampaignEligibleUser(Campaign campaign, CustomRedeemDTO customRedeemDTO) {
        CustomCampaignEligibleUser campaignEligibleUser = new CustomCampaignEligibleUser();
        campaignEligibleUser.setCampaign(campaign);
        campaignEligibleUser.setUsername(customRedeemDTO.getMobileNumber());
        campaignEligibleUser.setRecordedDate(new Date());
        campaignEligibleUser.setAccountNumber(customRedeemDTO.getAccountNumber());
        campaignEligibleUser.setViewName(campaign.getEventAttribute().getCustomCbsQuery().getQueryCode());
        return campaignEligibleUser;
    }

    public static CustomLogTracker convertToCustomRedeemMapper(CustomRedeemDTO customRedeemDTO, Campaign campaign) {
        CustomLogTracker customLogTracker = new CustomLogTracker();
        customLogTracker.setTrackId(customRedeemDTO.getId());
        customLogTracker.setCampaignId(campaign.getId());
        return customLogTracker;
    }
}
