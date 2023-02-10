package com.f1soft.campaign.web.mapper;

import com.f1soft.campaign.common.util.DateFormat;
import com.f1soft.campaign.common.util.DateFormatter;
import com.f1soft.campaign.entities.model.ApplicationUser;
import com.f1soft.campaign.entities.model.BookingLog;
import com.f1soft.campaign.entities.model.Campaign;
import com.f1soft.campaign.web.campaign.dto.CampaignDetailDTO;
import com.f1soft.campaign.web.dto.BookingLogDTO;
import com.f1soft.campaign.web.dto.CampaignListResponse;
import com.f1soft.campaign.web.dto.CampaignResponse;
import com.f1soft.campaign.web.users.dto.response.UserResponse;
import com.f1soft.campaign.web.util.PercentageHelper;

import java.util.List;
import java.util.stream.Collectors;

public class CampaignMapper {

    private CampaignMapper() {
    }

    public static CampaignListResponse convertToCampaign(List<Campaign> campaigns) {
        CampaignListResponse campaignListResponse = new CampaignListResponse();

        List<CampaignResponse> offerTransactionList = campaigns
                .stream()
                .map(CampaignMapper::convertToCampaignResponse)
                .collect(Collectors.toList());
        campaignListResponse.setCampaignList(offerTransactionList);
        return campaignListResponse;
    }

    public static CampaignResponse convertToCampaignResponse(Campaign campaign) {
        CampaignResponse campaignResponse = new CampaignResponse();
        campaignResponse.setId(campaign.getId());
        campaignResponse.setCampaignMode(campaign.getCampaignMode());
        campaignResponse.setTitle(campaign.getTitle());
        campaignResponse.setDescription(campaign.getDescription());
        campaignResponse.setPromoCode(campaign.getPromoCode());
        campaignResponse.setStartDate(DateFormatter.convertToString(campaign.getStartDate(), DateFormat.DATE_FORMAT));
        campaignResponse.setEndDate(DateFormatter.convertToString(campaign.getEndDate(), DateFormat.DATE_FORMAT));
        campaignResponse.setTotalIssued(campaign.getTotalIssued());
        campaignResponse.setUsagePerCustomer(campaign.getUsagePerCostumer());
        campaignResponse.setActive(campaign.getActive());
        return campaignResponse;
    }

    public static CampaignResponse convertToCampaignResponse(Campaign campaign, Integer totalConsumed) {

        CampaignResponse campaignResponse = new CampaignResponse();
        campaignResponse.setId(campaign.getId());
        campaignResponse.setCampaignMode(campaign.getCampaignMode());
        campaignResponse.setTitle(campaign.getTitle());
        campaignResponse.setShortDescription(campaign.getShortDescription());
        campaignResponse.setDescription(campaign.getDescription());
        campaignResponse.setPromoCode(campaign.getPromoCode());
        campaignResponse.setStartDate(DateFormatter.convertToString(campaign.getStartDate(), DateFormat.DATE_FORMAT));
        campaignResponse.setEndDate(DateFormatter.convertToString(campaign.getEndDate(), DateFormat.DATE_FORMAT));
        campaignResponse.setTotalIssued(campaign.getTotalIssued());
        campaignResponse.setUsagePerCustomer(campaign.getUsagePerCostumer());
        campaignResponse.setActive(campaign.getActive());
        campaignResponse.setTotalConsumed(totalConsumed);
        campaignResponse.setStatus(campaign.getStatus());

        return campaignResponse;
    }

    public static BookingLogDTO convertToBookingLogDTO(BookingLog bookingLog) {

        BookingLogDTO bookingLogDTO = new BookingLogDTO();

        bookingLogDTO.setBooked(bookingLog.getTotalBooked());
        bookingLogDTO.setUsed(bookingLog.getTotalUsed());
        bookingLogDTO.setIssued(bookingLog.getCampaign().getTotalIssued());
        bookingLogDTO.setCampaign(convertToCampaignResponse(bookingLog.getCampaign()));

        return bookingLogDTO;
    }

    public static BookingLogDTO convertToBookingLogDTO(Campaign campaign) {

        BookingLogDTO bookingLogDTO = new BookingLogDTO();

        bookingLogDTO.setBooked(0);
        bookingLogDTO.setUsed(0);
        bookingLogDTO.setIssued(campaign.getTotalIssued());
        bookingLogDTO.setCampaign(convertToCampaignResponse(campaign));

        return bookingLogDTO;
    }

    public static CampaignDetailDTO convertToCampaignDetailResponse(Campaign campaign, int totalConsumed) {

        CampaignDetailDTO campaignDetailDTO = new CampaignDetailDTO();

        campaignDetailDTO.setId(campaign.getId());
        campaignDetailDTO.setCampaignMode(campaign.getCampaignMode());
        campaignDetailDTO.setTitle(campaign.getTitle());
        campaignDetailDTO.setShortDescription(campaign.getShortDescription());
        campaignDetailDTO.setDescription(campaign.getDescription());
        campaignDetailDTO.setPromoCode(campaign.getPromoCode());
        campaignDetailDTO.setStartDate(DateFormatter.convertToString(campaign.getStartDate(), DateFormat.DATE_FORMAT));
        campaignDetailDTO.setEndDate(DateFormatter.convertToString(campaign.getEndDate(), DateFormat.DATE_FORMAT));
        campaignDetailDTO.setTotalIssued(campaign.getTotalIssued());
        campaignDetailDTO.setUsagePerCustomer(campaign.getUsagePerCostumer());
        campaignDetailDTO.setActive(campaign.getActive());
        campaignDetailDTO.setTotalConsumed(totalConsumed);
        campaignDetailDTO.setStatus(campaign.getStatus());

        campaignDetailDTO.setRedeemCount(campaign.getCampaignTotalRedeem().getRedeemCount());
        campaignDetailDTO.setSpendingLimit(campaign.getCampaignTotalRedeem().getOfferAmount());
        campaignDetailDTO.setSpentAmount(campaign.getCampaignTotalRedeem().getRedeemAmount());
        campaignDetailDTO.setRedeemLimit(campaign.getTotalIssued());
        campaignDetailDTO.setSpentPercentage(PercentageHelper.convertToRemainingPercentage(campaign.getCampaignTotalRedeem().getRedeemAmount(), campaign.getCampaignTotalRedeem().getOfferAmount()));
        campaignDetailDTO.setRedeemPercentage(PercentageHelper.convertToRemainingPercentage(campaign.getCampaignTotalRedeem().getRedeemCount().doubleValue(), campaign.getTotalIssued().doubleValue()));

        return campaignDetailDTO;
    }

    public static UserResponse convertToCampaignCreatorProfileResponse(ApplicationUser applicationUser) {
        UserResponse userResponse = new UserResponse();
        userResponse.setId(applicationUser.getId());
        userResponse.setUserName(applicationUser.getUsername());
        userResponse.setName(applicationUser.getName());
        userResponse.setMobileNumber(applicationUser.getMobileNumber());
        userResponse.setEmailAddress(applicationUser.getEmailAddress());
        userResponse.setActive(applicationUser.getActive());
        return userResponse;
    }
}
