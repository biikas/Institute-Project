package com.f1soft.campaign.web.mapper;

import com.f1soft.campaign.common.cbs.dto.CustomerDetailDTO;
import com.f1soft.campaign.common.enums.OfferTransactionStatusEnum;
import com.f1soft.campaign.common.util.BeanUtil;
import com.f1soft.campaign.entities.model.*;
import com.f1soft.campaign.repository.OfferPackageRepository;

import java.util.Date;
import java.util.List;

/**
 * @author Shreetika Panta
 */
public class EligibleCustomerMapper {

    private EligibleCustomerMapper() {
    }

    public static RegistrationCampaignUser saveCampaignUser(CustomerDetailDTO customerDetailDTO, Campaign campaign) {
        RegistrationCampaignUser registrationCampaignUser = new RegistrationCampaignUser();
        registrationCampaignUser.setRegistrationDate(customerDetailDTO.getRegistrationDate());
        registrationCampaignUser.setAccountNumber(customerDetailDTO.getAccountNumber());
        registrationCampaignUser.setUsername(customerDetailDTO.getUsername());
        registrationCampaignUser.setRecordedDate(new Date());
        registrationCampaignUser.setProfileId(customerDetailDTO.getProfileId());
        registrationCampaignUser.setAccountType(customerDetailDTO.getAccountType());
        registrationCampaignUser.setCampaignId(campaign.getId());

        return registrationCampaignUser;
    }

    public static OfferTransaction saveOfferTxn(CustomerDetailDTO customerDetailDTO, Campaign campaign, CampaignOffer campaignOffer, Channel channel) {
        OfferTransaction offerTransaction = new OfferTransaction();
        offerTransaction.setAccountNumber(customerDetailDTO.getAccountNumber());
        offerTransaction.setMobileNumber(customerDetailDTO.getUsername());
        offerTransaction.setAmount(Double.parseDouble(campaignOffer.getValue()));
        offerTransaction.setTransactionAmount(Double.parseDouble(campaignOffer.getValue()));
        offerTransaction.setCampaignOffer(campaignOffer);
        offerTransaction.setPromoCode(campaign.getPromoCode());
        offerTransaction.setRecordedDate(new Date());
        offerTransaction.setCampaign(campaign);
        offerTransaction.setStatus("ACTIVE");
        offerTransaction.setProfileId(customerDetailDTO.getProfileId());
        offerTransaction.setTransactionStatus(OfferTransactionStatusEnum.PENDING.name());
        offerTransaction.setClaimDate(new Date());
        offerTransaction.setChannel(channel);
        offerTransaction.setCustomerName(customerDetailDTO.getCustomerName());

        return offerTransaction;
    }

    public static DataPackageCampaignUser convertToDataPackageCampaignUser(OfferTransaction offerTransaction, CustomerDetailDTO customerDetailDTO, Campaign campaign, CampaignOffer campaignOffer) {
        List<OfferPackage> offerPackages = BeanUtil.getBean(OfferPackageRepository.class).findByOfferModeId(campaignOffer.getOfferMode().getId());

        DataPackageCampaignUser dataPackageCampaignUser = null;
        if (offerPackages.size() > 0) {
            OfferPackage offerMode = offerPackages.stream().filter(f -> f.getCode().equalsIgnoreCase(campaignOffer.getValue())).findAny().get();


            PackageItem packageItem = offerMode.getPackageItems().stream().filter(f -> customerDetailDTO.getUsername().matches(f.getRegex())).findAny().get();

            dataPackageCampaignUser = new DataPackageCampaignUser();
            dataPackageCampaignUser.setCampaign(campaign);
            dataPackageCampaignUser.setOfferTransaction(offerTransaction);
            dataPackageCampaignUser.setPackageItem(packageItem);
            dataPackageCampaignUser.setUsername(customerDetailDTO.getUsername());
        }
        return dataPackageCampaignUser;
    }
}
