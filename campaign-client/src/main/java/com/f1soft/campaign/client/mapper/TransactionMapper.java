package com.f1soft.campaign.client.mapper;

import com.f1soft.campaign.client.dto.TransactionRequesterData;
import com.f1soft.campaign.client.util.BookingHelper;
import com.f1soft.campaign.common.enums.OfferModeEnum;
import com.f1soft.campaign.common.enums.OfferTransactionStatusEnum;
import com.f1soft.campaign.common.util.BeanUtil;
import com.f1soft.campaign.entities.model.*;
import com.f1soft.campaign.repository.OfferPackageRepository;

import java.util.Date;
import java.util.List;

/**
 * @author Prajwol hada
 */
public class TransactionMapper {

    private TransactionMapper() {

    }

    public static OfferTransaction convertToOfferTransaction(TransactionRequesterData transactionRequesterData, Channel channel, String serviceName, String serviceCode) {
        OfferTransaction offerTransaction = new OfferTransaction();

        CampaignOffer campaignOffer = BookingHelper.campaignOfferByAmount(transactionRequesterData.getCampaign(), transactionRequesterData.getTransactionAmount());
        offerTransaction.setTransactionId(transactionRequesterData.getTransactionId());
        offerTransaction.setMobileNumber(transactionRequesterData.getMobileNumber());
        offerTransaction.setCampaign(transactionRequesterData.getCampaign());
        offerTransaction.setRecordedDate(new Date());
        offerTransaction.setPromoCode(transactionRequesterData.getCampaign().getPromoCode());
        offerTransaction.setCampaignOffer(campaignOffer);
        offerTransaction.setAmount(BookingHelper.calculateAmount(transactionRequesterData.getTransactionAmount(), transactionRequesterData.getCampaign(), transactionRequesterData.getMobileNumber()));
        offerTransaction.setStatus("ACTIVE");
        offerTransaction.setTransactionAmount(transactionRequesterData.getTransactionAmount());
        offerTransaction.setServiceCode(serviceCode);
        offerTransaction.setServiceName(serviceName);
        offerTransaction.setBookingId(transactionRequesterData.getBookingId());
        offerTransaction.setAccountNumber(transactionRequesterData.getAccountNumber());
        offerTransaction.setChannel(channel);
        offerTransaction.setProfileId(transactionRequesterData.getProfileId());
        offerTransaction.setTransactionStatus(OfferTransactionStatusEnum.PENDING.name());
        if (campaignOffer.getOfferMode().getCode().equalsIgnoreCase(OfferModeEnum.GIFTCARD.name())) {
            offerTransaction.setTransactionStatus(OfferTransactionStatusEnum.SUCCESS.name());
        }
        offerTransaction.setCustomerName(transactionRequesterData.getCustomerName());
        offerTransaction.setClaimDate(new Date());
        return offerTransaction;
    }

    public static DataPackageCampaignUser convertToDataPackageCampaignUser(OfferTransaction offerTransaction, TransactionRequesterData transactionRequesterData) {
        CampaignOffer campaignOffer = BookingHelper.campaignOfferByAmount(transactionRequesterData.getCampaign(), transactionRequesterData.getTransactionAmount());

        DataPackageCampaignUser dataPackageCampaignUser = null;
        List<OfferPackage> offerPackages = BeanUtil.getBean(OfferPackageRepository.class).findByOfferModeId(campaignOffer.getOfferMode().getId());
        if (offerPackages.size() > 0) {
            OfferPackage offerMode = offerPackages.stream().filter(f -> f.getCode().equalsIgnoreCase(campaignOffer.getValue())).findAny().get();

            PackageItem packageItem = offerMode.getPackageItems().stream().filter(f -> transactionRequesterData.getMobileNumber().matches(f.getRegex())).findAny().get();

            dataPackageCampaignUser = new DataPackageCampaignUser();
            dataPackageCampaignUser.setCampaign(transactionRequesterData.getCampaign());
            dataPackageCampaignUser.setOfferTransaction(offerTransaction);
            dataPackageCampaignUser.setPackageItem(packageItem);
            dataPackageCampaignUser.setUsername(transactionRequesterData.getMobileNumber());
        }
        return dataPackageCampaignUser;
    }
}
