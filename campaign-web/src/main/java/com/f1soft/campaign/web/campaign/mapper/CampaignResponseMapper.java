package com.f1soft.campaign.web.campaign.mapper;

import com.f1soft.campaign.common.util.DateFormatter;
import com.f1soft.campaign.entities.model.*;
import com.f1soft.campaign.web.campaign.dto.NtransactionDTO;
import com.f1soft.campaign.web.campaign.dto.response.CampaignDetailResponse;
import com.f1soft.campaign.web.campaign.dto.response.OfferDetailResponse;
import com.f1soft.campaign.web.campaign.dto.response.ServiceDetailResponse;
import com.f1soft.campaign.web.constant.Constant;
import com.f1soft.campaign.web.dto.ProfileDTO;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CampaignResponseMapper {

    private CampaignResponseMapper() {

    }

    public static CampaignDetailResponse campaignResponseMapper(Campaign campaign, String imagePath, List<CampaignEligibleProfile> campaignEligibleProfileList, Optional<EventAttribute> eventAttribute) {
        CampaignDetailResponse campaignDetailResponse = new CampaignDetailResponse();

        campaignDetailResponse.setId(campaign.getId());
        campaignDetailResponse.setTitle(campaign.getTitle());
        campaignDetailResponse.setShortDescription(campaign.getShortDescription());
        campaignDetailResponse.setDescription(campaign.getDescription());
        campaignDetailResponse.setPromoCode(campaign.getPromoCode());
        campaignDetailResponse.setStartDate(DateFormatter.convertToString(campaign.getStartDate()));
        campaignDetailResponse.setEndDate(DateFormatter.convertToString(campaign.getEndDate()));
        campaignDetailResponse.setTotalCodeToIssue(campaign.getTotalIssued());
        campaignDetailResponse.setUsageLimitPerCustomer(campaign.getUsagePerCostumer());
        campaignDetailResponse.setBookingExpiryTime(campaign.getBookingExpiryTime());
        campaignDetailResponse.setPromoCodeMode(campaign.getPromoCodeMode());
        if (campaign.getImage() != null) {
            campaignDetailResponse.setImagePath(imagePath);
            campaignDetailResponse.setImage(campaign.getImage());
        }
        campaignDetailResponse.setCampaignMode(campaign.getCampaignMode());
        campaignDetailResponse.setAllowedUsers(campaign.getAllowedUsers());
        campaignDetailResponse.setPolicy(campaign.getPolicy());
        campaignDetailResponse.setStatus(campaign.getStatus());
        campaignDetailResponse.setEventType(campaign.getEventType());
        campaignDetailResponse.setOfferAccount(campaign.getOfferAccount());
        campaignDetailResponse.setLink(campaign.getOfferLink());
        if (campaign.getEventTypeId() != null) {
            campaignDetailResponse.setEventTypeId(campaign.getEventTypeId().getId());
        }

        List<OfferDetailResponse> offerDetailResponseList = campaign.getCampaignOffer().stream()
                .filter(campaignOffer -> campaignOffer.getActive() == 'Y')
                .sorted(Comparator.comparingDouble(CampaignOffer::getMinAmount))
                .map(CampaignResponseMapper::convertToOfferDetailResponse)
                .collect(Collectors.toList());

        campaignDetailResponse.setOffers(offerDetailResponseList);

        Optional<CampaignAllowedChannel> optionalWebChannel = campaign.getCampaignAllowedChannels().stream()
                .filter(campaignAllowedChannel -> campaignAllowedChannel.getActive() == 'Y')
                .filter(campaignAllowedChannel -> campaignAllowedChannel.getChannel().getCode().equalsIgnoreCase(Constant.Channel.WEB))
                .findAny();

        if (optionalWebChannel.isPresent() && optionalWebChannel.get().getChannel().getCode().equalsIgnoreCase(Constant.Channel.WEB)) {
            campaignDetailResponse.setIsWeb('Y');
        } else {
            campaignDetailResponse.setIsWeb('N');
        }

        Optional<CampaignAllowedChannel> optionalMobileChannel = campaign.getCampaignAllowedChannels().stream()
                .filter(campaignAllowedChannel -> campaignAllowedChannel.getActive() == 'Y')
                .filter(campaignAllowedChannel -> campaignAllowedChannel.getChannel().getCode().equalsIgnoreCase(Constant.Channel.MOBILE))
                .findAny();

        if (optionalMobileChannel.isPresent() && optionalMobileChannel.get().getChannel().getCode().equalsIgnoreCase(Constant.Channel.MOBILE)) {
            campaignDetailResponse.setIsMobile('Y');
        } else {
            campaignDetailResponse.setIsMobile('N');
        }

        List<ServiceDetailResponse> serviceDetailResponses = campaign.getCampaignEligibleServices().stream()
                .filter(campaignEligibleService -> campaignEligibleService.getActive() == 'Y')
                .map(campaignEligibleService -> {
                    ServiceDetailResponse serviceDetailResponse = new ServiceDetailResponse();
                    serviceDetailResponse.setCode(campaignEligibleService.getServiceCode());
                    serviceDetailResponse.setName(campaignEligibleService.getServiceName());
                    serviceDetailResponse.setActive(campaignEligibleService.getActive());
                    serviceDetailResponse.setModule(campaignEligibleService.getModule());

                    return serviceDetailResponse;
                }).collect(Collectors.toList());

        campaignDetailResponse.setServices(serviceDetailResponses);

        List<ProfileDTO> profileDTOList = campaignEligibleProfileList.stream()
                .map(CampaignResponseMapper::convertToProfileResponse)
                .collect(Collectors.toList());

        campaignDetailResponse.setProfile(profileDTOList);


        if(eventAttribute.isPresent()){
            NtransactionDTO ntransaction= new NtransactionDTO();
            ntransaction.setMinimumAmount(eventAttribute.get().getMinimumAmount());
            ntransaction.setNoOfTransaction(eventAttribute.get().getCount());
            campaignDetailResponse.setNtransaction(ntransaction);

            if (eventAttribute.get().getCustomCbsQuery() != null) {
                campaignDetailResponse.setCustomCbsQueryId(eventAttribute.get().getCustomCbsQuery().getId());
                campaignDetailResponse.setViewName(eventAttribute.get().getCustomCbsQuery().getQueryDescription());
            }
        }
        return campaignDetailResponse;
    }

    private static ProfileDTO convertToProfileResponse(CampaignEligibleProfile campaignEligibleProfile) {
        ProfileDTO profileDTO = new ProfileDTO();
        profileDTO.setId(campaignEligibleProfile.getProfileId());
        profileDTO.setName(campaignEligibleProfile.getProfileName());
        return profileDTO;
    }

    public static OfferDetailResponse convertToOfferDetailResponse(CampaignOffer campaignOffer) {
        OfferDetailResponse offerDetailResponse = new OfferDetailResponse();

        offerDetailResponse.setId(campaignOffer.getId());
        offerDetailResponse.setOfferMode(campaignOffer.getOfferMode().getCode());
        if (campaignOffer.getCommissionType() != null) {
            offerDetailResponse.setCommissionType(campaignOffer.getCommissionType());
        }
        offerDetailResponse.setMinAmount(campaignOffer.getMinAmount());
        offerDetailResponse.setMaxAmount(campaignOffer.getMaxAmount());
        offerDetailResponse.setValue(campaignOffer.getValue());

        return offerDetailResponse;
    }
}
