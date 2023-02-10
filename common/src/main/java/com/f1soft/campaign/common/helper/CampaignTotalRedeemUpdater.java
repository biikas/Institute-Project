package com.f1soft.campaign.common.helper;

import com.f1soft.campaign.common.constant.MsgConstant;
import com.f1soft.campaign.common.dto.ServerResponse;
import com.f1soft.campaign.common.exception.IllegalStateException;
import com.f1soft.campaign.common.util.ResponseMsg;
import com.f1soft.campaign.entities.model.Campaign;
import com.f1soft.campaign.entities.model.CampaignTotalRedeem;
import com.f1soft.campaign.repository.CampaignTotalRedeemRepository;
import lombok.Synchronized;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Optional;

/**
 * @Author Nitesh Poudel
 */
@Slf4j
@SuppressWarnings({"Duplicates"})
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
@Component
public class CampaignTotalRedeemUpdater {

    @Autowired
    private CampaignTotalRedeemRepository campaignTotalRedeemRepository;

    private CampaignTotalRedeemUpdater() {

    }

    @Synchronized
    public void updateCampaignTotalRedeem(Campaign campaign, Double amount) {

        Optional<CampaignTotalRedeem> campaignTotalRedeemOptional = campaignTotalRedeemRepository.getCampaignTotalRedeemByCampaignId(campaign.getId());

        if (campaignTotalRedeemOptional.isPresent()) {

            CampaignTotalRedeem campaignTotalRedeem = campaignTotalRedeemOptional.get();

            BigDecimal redeemAmount = new BigDecimal(campaignTotalRedeem.getRedeemAmount()).setScale(2, BigDecimal.ROUND_HALF_DOWN).add(new BigDecimal(amount).setScale(2, BigDecimal.ROUND_HALF_DOWN));

            int redeemCount = campaignTotalRedeem.getRedeemCount() + 1;

            campaignTotalRedeem.setRedeemAmount(redeemAmount.doubleValue());
            campaignTotalRedeem.setRedeemCount(redeemCount);
            campaignTotalRedeem.setLastUpdatedDate(new Date());

            campaignTotalRedeemRepository.save(campaignTotalRedeem);
        }
    }

    @Synchronized
    public ServerResponse validateAmountLimit(Campaign campaign, Double cashBackAmount) {
        ServerResponse serverResponse = new ServerResponse();

        CampaignTotalRedeem campaignTotalRedeem = campaignTotalRedeemRepository.getCampaignTotalRedeemByCampaignId(campaign.getId()).get();

        Double offerAmount = campaignTotalRedeem.getOfferAmount();
        Double redeemAmount = campaignTotalRedeem.getRedeemAmount();

        BigDecimal updatedRedeemAmount = new BigDecimal(redeemAmount).setScale(2, BigDecimal.ROUND_HALF_DOWN).add(new BigDecimal(cashBackAmount).setScale(2, BigDecimal.ROUND_HALF_DOWN));

        Double amountAfterRedeem = updatedRedeemAmount.doubleValue();

        if (amountAfterRedeem > offerAmount) {
            throw new IllegalStateException(ResponseMsg.failureResponse(MsgConstant.Customer.OFFER_QUOTA_EXCEEDED));
        }

        serverResponse.setSuccess(true);
        return serverResponse;
    }
}
