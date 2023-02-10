package com.f1soft.campaign.client.util;

import com.f1soft.campaign.common.enums.OfferModeEnum;
import com.f1soft.campaign.common.util.BeanUtil;
import com.f1soft.campaign.entities.model.Campaign;
import com.f1soft.campaign.entities.model.CampaignOffer;
import com.f1soft.campaign.entities.model.OfferPackage;
import com.f1soft.campaign.entities.model.PackageItem;
import com.f1soft.campaign.repository.OfferPackageRepository;

import java.sql.Timestamp;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Prajwol hada
 */
public class BookingHelper {

    private BookingHelper() {

    }

    public static String generateBookingId(String mobileNumber) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        Long time = timestamp.getTime();
        return time + "-" + mobileNumber;
    }

    public static Double calculateAmount(Double totalAmount, Campaign campaign, String mobileNumber) {
        Double cashBackAmount = 0.0;

        List<CampaignOffer> campaignOfferList = campaign.getCampaignOffer().stream()
                .filter(campaignOffer -> campaignOffer.getActive() == 'Y')
                .sorted(Comparator.comparingDouble(CampaignOffer::getMinAmount))
                .collect(Collectors.toList());

        for (CampaignOffer campaignOffer : campaignOfferList) {
            if (totalAmount >= campaignOffer.getMinAmount() && totalAmount <= campaignOffer.getMaxAmount()) {

                if (campaignOffer.getCommissionType() == null) {
                    if (campaignOffer.getOfferMode().getCode().equalsIgnoreCase(OfferModeEnum.DATAPACK.name())) {
                        Optional<OfferPackage> offerPackageOptional = BeanUtil.getBean(OfferPackageRepository.class).findByOfferModeAndCode(campaignOffer.getOfferMode().getId(), campaignOffer.getValue());

                        if (offerPackageOptional.isPresent()) {
                            OfferPackage offerPackage = offerPackageOptional.get();

                            PackageItem packageItem = offerPackage.getPackageItems().stream().filter(f -> mobileNumber.matches(f.getRegex())).findAny().get();

                            if (packageItem != null) {
                                cashBackAmount = packageItem.getAmount();
                            }
                        }
                    }
                    return cashBackAmount;
                } else if (campaignOffer.getCommissionType().equalsIgnoreCase("PERCENTAGE")) {
                    cashBackAmount = totalAmount * (Double.valueOf(campaignOffer.getValue()) / 100);
                    return cashBackAmount;
                } else {
                    cashBackAmount = Double.valueOf(campaignOffer.getValue());
                    return cashBackAmount;
                }
            }
        }
        return cashBackAmount;
    }


    public static CampaignOffer campaignOfferByAmount(Campaign campaign, Double amount) {

        List<CampaignOffer> campaignOffers = campaign.getCampaignOffer();

        return campaignOffers.stream()
                .filter(campaignOffer -> campaignOffer.getActive() == 'Y')
                .filter(campaignOffer -> campaignOffer.getMinAmount() <= amount && campaignOffer.getMaxAmount() >= amount)
                .findAny()
                .get();

    }
}
