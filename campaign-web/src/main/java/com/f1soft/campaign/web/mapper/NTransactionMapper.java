package com.f1soft.campaign.web.mapper;

import com.f1soft.campaign.client.util.BookingHelper;
import com.f1soft.campaign.common.cbs.dto.TransactionDTO;
import com.f1soft.campaign.common.enums.OfferTransactionStatusEnum;
import com.f1soft.campaign.common.util.BeanUtil;
import com.f1soft.campaign.entities.model.*;
import com.f1soft.campaign.repository.OfferPackageRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @author Shreetika Panta
 */
public class NTransactionMapper {

    public static OfferTransaction convertToSaveOfferTransaction(Campaign campaign, Channel channel, TransactionDTO transactionDTO) {
        CampaignOffer campaignOffer = BookingHelper.campaignOfferByAmount(campaign, transactionDTO.getAmount());
        OfferTransaction offerTransaction = new OfferTransaction();
        offerTransaction.setAccountNumber(transactionDTO.getAccountNumber());
        offerTransaction.setAmount(BookingHelper.calculateAmount(transactionDTO.getAmount(), campaign, null));
        offerTransaction.setMobileNumber(transactionDTO.getUsername());
        offerTransaction.setPromoCode(campaign.getPromoCode());
        offerTransaction.setRecordedDate(new Date());
//        offerTransaction.setRemarks(OfferTransactionStatusEnum.SUCCESS.name());
        offerTransaction.setCampaign(campaign);
        offerTransaction.setCampaignOffer(campaignOffer);
        offerTransaction.setStatus("ACTIVE");
        offerTransaction.setTransactionAmount(transactionDTO.getAmount());
        offerTransaction.setServiceCode(transactionDTO.getTransactionCode());
        offerTransaction.setProfileId(transactionDTO.getProfileId());
        offerTransaction.setTransactionStatus(OfferTransactionStatusEnum.PENDING.name());
        offerTransaction.setClaimDate(new Date());
        offerTransaction.setTransactionDate(transactionDTO.getTransactionDate());
        offerTransaction.setChannel(channel);
        offerTransaction.setCustomerName(transactionDTO.getCustomerName());
        return offerTransaction;
    }

    public static TransactionCampaignUser convertToSaveTransactionCampaignUser(Campaign campaign, TransactionDTO transactionDTO) {
        TransactionCampaignUser transactionCampaignUser = new TransactionCampaignUser();
        transactionCampaignUser.setCampaign(campaign);
        transactionCampaignUser.setRecordedDate(new Date());
        transactionCampaignUser.setUsername(transactionDTO.getUsername());
        return transactionCampaignUser;
    }

    public static DataPackageCampaignUser convertToDataPackageCampaignUser(OfferTransaction offerTransaction, Campaign campaign, TransactionDTO transactionDTO) {
        CampaignOffer campaignOffer = BookingHelper.campaignOfferByAmount(campaign, transactionDTO.getAmount());

        DataPackageCampaignUser dataPackageCampaignUser = null;
        List<OfferPackage> offerPackages = BeanUtil.getBean(OfferPackageRepository.class).findByOfferModeId(campaignOffer.getOfferMode().getId());
        if (offerPackages.size() > 0) {
            OfferPackage offerMode = offerPackages.stream().filter(f -> f.getCode().equalsIgnoreCase(campaignOffer.getValue())).findAny().get();

            PackageItem packageItem = offerMode.getPackageItems().stream().filter(f -> transactionDTO.getUsername().matches(f.getRegex())).findAny().get();

            dataPackageCampaignUser = new DataPackageCampaignUser();
            dataPackageCampaignUser.setCampaign(campaign);
            dataPackageCampaignUser.setOfferTransaction(offerTransaction);
            dataPackageCampaignUser.setPackageItem(packageItem);
            dataPackageCampaignUser.setUsername(transactionDTO.getUsername());
        }
        return dataPackageCampaignUser;
    }
}
