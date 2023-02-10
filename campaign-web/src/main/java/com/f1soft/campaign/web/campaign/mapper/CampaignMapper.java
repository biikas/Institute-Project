package com.f1soft.campaign.web.campaign.mapper;

import com.f1soft.campaign.common.enums.PromoCodeModeEnum;
import com.f1soft.campaign.common.enums.UserTypesEnum;
import com.f1soft.campaign.common.util.DateFormatter;
import com.f1soft.campaign.entities.model.*;
import com.f1soft.campaign.web.campaign.dto.CampaignServiceDTO;
import com.f1soft.campaign.web.campaign.dto.OfferCreateDTO;
import com.f1soft.campaign.web.campaign.dto.UserDTO;
import com.f1soft.campaign.web.campaign.dto.request.campaign.CreateCampaignRequest;
import com.f1soft.campaign.web.campaign.dto.response.ModifyServiceDTO;
import com.f1soft.campaign.web.constant.Constant;
import com.f1soft.campaign.web.constant.FileUploadProcessConstant;
import com.f1soft.campaign.web.dto.ProfileDTO;

import java.util.Date;
import java.util.Optional;

/**
 * @author Prajwol hada
 */
public class CampaignMapper {

    private CampaignMapper() {

    }

    public static Campaign mapToCampaign(CreateCampaignRequest createCampaignRequest, ApplicationUser applicationUser, EventType eventType) {
        Campaign campaign = new Campaign();

        campaign.setActive('Y');
        campaign.setBookingExpiryTime(createCampaignRequest.getExpiryTime());
        campaign.setCreatedBy(applicationUser);
        campaign.setTitle(createCampaignRequest.getTitle());
        campaign.setShortDescription(createCampaignRequest.getShortDescription());
        campaign.setDescription(createCampaignRequest.getDescription());
        campaign.setPolicy(createCampaignRequest.getPolicy());
        campaign.setPromoCode(createCampaignRequest.getPromoCode());
        campaign.setStartDate(DateFormatter.convertToDate(createCampaignRequest.getStartDate()));
        campaign.setEndDate(DateFormatter.convertToDate(createCampaignRequest.getEndDate()));
        campaign.setTotalIssued(createCampaignRequest.getTotalIssued());
        campaign.setUsagePerCostumer(createCampaignRequest.getUsagePerCustomer());
        campaign.setEventType(eventType.getType());
        campaign.setImage(createCampaignRequest.getImage());
        campaign.setCreatedDate(new Date());
        campaign.setCampaignMode(createCampaignRequest.getCampaignMode());
        campaign.setOfferLink(createCampaignRequest.getLink());
        campaign.setStatus(Constant.CampaignStatus.ACTIVE);
        campaign.setOfferAccount(createCampaignRequest.getOfferAccount());
        campaign.setEventTypeId(eventType);

        if (createCampaignRequest.getAllowedUsers().equalsIgnoreCase(UserTypesEnum.ALL.name())) {
            campaign.setAllowedUsers(UserTypesEnum.ALL.name());
        } else if (createCampaignRequest.getAllowedUsers().equalsIgnoreCase(UserTypesEnum.PROFILE.name())) {
            campaign.setAllowedUsers(UserTypesEnum.PROFILE.name());
        } else if (createCampaignRequest.getAllowedUsers().equalsIgnoreCase(UserTypesEnum.BULK.name())) {
            campaign.setAllowedUsers(UserTypesEnum.BULK.name());
        }

        if (createCampaignRequest.getPromoCode() != null) {
            campaign.setPromoCodeMode(PromoCodeModeEnum.MANUAL.name());
        } else {
            campaign.setPromoCodeMode(PromoCodeModeEnum.AUTO.name());
        }

        return campaign;

    }

    public static CampaignEligibleService mapToCampaignEligibleService(CampaignServiceDTO serviceDTO, Optional<Service> serviceOptional, Campaign campaign) {

        CampaignEligibleService campaignEligibleService = new CampaignEligibleService();
        campaignEligibleService.setActive('Y');
        if (serviceOptional.isPresent()) {
            Service service = serviceOptional.get();
            campaignEligibleService.setServiceCode(service.getCode());
            campaignEligibleService.setServiceName(service.getName());
            campaignEligibleService.setModule(service.getModule());
            campaignEligibleService.setMode(Constant.ServiceMode.SERVICE);
        } else {
            campaignEligibleService.setServiceCode(serviceDTO.getCode());
            campaignEligibleService.setServiceName(serviceDTO.getName());
            campaignEligibleService.setModule(serviceDTO.getModule());
            campaignEligibleService.setMode(Constant.ServiceMode.MANUAL);
        }
        campaignEligibleService.setCampaign(campaign);

        return campaignEligibleService;
    }

    public static CampaignOffer mapToCampaignOffer(OfferCreateDTO offerCreateDTO, Campaign campaign, OfferMode offerMode) {

        CampaignOffer campaignOffer = new CampaignOffer();

        campaignOffer.setActive('Y');
        campaignOffer.setCampaign(campaign);
        campaignOffer.setOfferMode(offerMode);
        campaignOffer.setMinAmount(offerCreateDTO.getMinAmount());
        campaignOffer.setMaxAmount(offerCreateDTO.getMaxAmount());
        campaignOffer.setValue(offerCreateDTO.getValue());
        if (offerCreateDTO.getCommissionType() != null) {
            campaignOffer.setCommissionType(offerCreateDTO.getCommissionType());
        }

        return campaignOffer;
    }

    public static CampaignEligibleProfile mapToCampaignEligibleProfile(ProfileDTO profileDTO, Campaign campaign) {
        CampaignEligibleProfile campaignEligibleProfile = new CampaignEligibleProfile();
        campaignEligibleProfile.setActive('Y');
        campaignEligibleProfile.setCampaign(campaign);
        campaignEligibleProfile.setProfileId(profileDTO.getId());
        campaignEligibleProfile.setProfileName(profileDTO.getName());

        return campaignEligibleProfile;
    }

    public static CampaignEligibleService mapToCampaignEligibleService(ModifyServiceDTO serviceDTO, Optional<Service> serviceOptional, Campaign campaign) {

        CampaignEligibleService campaignEligibleService = new CampaignEligibleService();
        campaignEligibleService.setActive('Y');
        if (serviceOptional.isPresent()) {
            Service service = serviceOptional.get();
            campaignEligibleService.setServiceCode(service.getCode());
            campaignEligibleService.setServiceName(service.getName());
            campaignEligibleService.setModule(service.getModule());
            campaignEligibleService.setMode(Constant.ServiceMode.SERVICE);
        } else {
            campaignEligibleService.setServiceCode(serviceDTO.getCode());
            campaignEligibleService.setServiceName(serviceDTO.getName());
            campaignEligibleService.setModule(serviceDTO.getModule());
            campaignEligibleService.setMode(Constant.ServiceMode.MANUAL);
        }
        campaignEligibleService.setCampaign(campaign);

        return campaignEligibleService;
    }

    public static CampaignEligibleUser mapToCampaignEligibleUser(UserDTO userDTO, Campaign campaign, String fileName) {
        CampaignEligibleUser campaignEligibleUser = new CampaignEligibleUser();
        campaignEligibleUser.setUserName(userDTO.getUsername());
        if (campaign.getPromoCode() != null) {
            campaignEligibleUser.setPromoCode(campaign.getPromoCode());
        } else {
            campaignEligibleUser.setPromoCode(userDTO.getPromoCode());
        }
        campaignEligibleUser.setCampaign(campaign);
        campaignEligibleUser.setBulkFileName(fileName);

        return campaignEligibleUser;
    }

    public static Campaign mapToCampaign(CreateCampaignRequest createCampaignRequest, ApplicationUser applicationUser, EventType eventType, String promoCode) {
        Campaign campaign = new Campaign();

        campaign.setActive('Y');
        campaign.setBookingExpiryTime(createCampaignRequest.getExpiryTime());
        campaign.setCreatedBy(applicationUser);
        campaign.setTitle(createCampaignRequest.getTitle());
        campaign.setShortDescription(createCampaignRequest.getShortDescription());
        campaign.setDescription(createCampaignRequest.getDescription());
        campaign.setPolicy(createCampaignRequest.getPolicy());
        campaign.setPromoCode(promoCode);
        campaign.setStartDate(DateFormatter.convertToDate(createCampaignRequest.getStartDate()));
        campaign.setEndDate(DateFormatter.convertToDate(createCampaignRequest.getEndDate()));
        campaign.setTotalIssued(createCampaignRequest.getTotalIssued());
        campaign.setUsagePerCostumer(createCampaignRequest.getUsagePerCustomer());
        campaign.setEventType(eventType.getType());
        campaign.setImage(createCampaignRequest.getImage());
        campaign.setCreatedDate(new Date());
        campaign.setCampaignMode(createCampaignRequest.getCampaignMode());
        campaign.setOfferLink(createCampaignRequest.getLink());
        campaign.setStatus(Constant.CampaignStatus.ACTIVE);
        campaign.setOfferAccount(createCampaignRequest.getOfferAccount());
        campaign.setEventTypeId(eventType);
        campaign.setPromoCodeMode(PromoCodeModeEnum.AUTO.name());

        if (createCampaignRequest.getAllowedUsers().equalsIgnoreCase(UserTypesEnum.ALL.name())) {
            campaign.setAllowedUsers(UserTypesEnum.ALL.name());
        } else if (createCampaignRequest.getAllowedUsers().equalsIgnoreCase(UserTypesEnum.PROFILE.name())) {
            campaign.setAllowedUsers(UserTypesEnum.PROFILE.name());
        } else if (createCampaignRequest.getAllowedUsers().equalsIgnoreCase(UserTypesEnum.BULK.name())) {
            campaign.setAllowedUsers(UserTypesEnum.BULK.name());
        }

        return campaign;

    }

    public static CampaignTotalRedeem mapToCampaignTotalRedeem(Campaign campaign, Double offerAmount) {

        CampaignTotalRedeem campaignTotalRedeem = new CampaignTotalRedeem();

        campaignTotalRedeem.setRedeemCount(0);
        campaignTotalRedeem.setRedeemAmount(0D);
        campaignTotalRedeem.setCampaign(campaign);
        campaignTotalRedeem.setRecordedDate(new Date());
        campaignTotalRedeem.setOfferAmount(offerAmount);

        return campaignTotalRedeem;
    }

    public static CampaignTotalRedeem mapToModifyCampaignTotalRedeem(CampaignTotalRedeem campaignTotalRedeem,Campaign campaign, Double offerAmount) {

        campaignTotalRedeem.setCampaign(campaign);
        campaignTotalRedeem.setLastUpdatedDate(new Date());
        campaignTotalRedeem.setOfferAmount(offerAmount);

        return campaignTotalRedeem;
    }

    public static CampaignExcelFiles mapToCampaignExcelFile(String fileName, Campaign campaign) {
        CampaignExcelFiles campaignExcelFiles = new CampaignExcelFiles();

        campaignExcelFiles.setCampaign(campaign);
        campaignExcelFiles.setFileName(fileName);
        campaignExcelFiles.setProcessStatus(FileUploadProcessConstant.FILE_ADDED);

        return campaignExcelFiles;
    }
}
