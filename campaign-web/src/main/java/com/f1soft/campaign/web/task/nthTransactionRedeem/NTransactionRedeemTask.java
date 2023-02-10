package com.f1soft.campaign.web.task.nthTransactionRedeem;

import com.f1soft.campaign.common.cbs.dto.CampaignJob;
import com.f1soft.campaign.common.enums.EventTypeEnum;
import com.f1soft.campaign.common.util.DateHelper;
import com.f1soft.campaign.common.util.ThreadUtil;
import com.f1soft.campaign.entities.model.Campaign;
import com.f1soft.campaign.entities.model.CampaignEligibleService;
import com.f1soft.campaign.repository.CampaignRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Shreetika Panta
 */

@Slf4j
@Component
public class NTransactionRedeemTask {

    @Autowired
    private CampaignRepository campaignRepository;
    @Autowired
    private NTransactionRedeemExecutor nTransactionRedeemExecutor;

    public void call() {
        while (true) {
            List<Campaign> campaigns = campaignRepository.getCampaignByEventType(EventTypeEnum.NTRANSACTION.name());
            List<Campaign> campaignList = getActiveCampaign(campaigns);

            if (campaignList.size() > 0) {
                for (Campaign campaign : campaignList) {
                    List<CampaignEligibleService> campaignEligibleServices = campaign.getCampaignEligibleServices().stream()
                            .filter(f -> f.getActive() == 'Y').collect(Collectors.toList());
                    if (campaignEligibleServices.size() > 0) {
                        campaignEligibleServices.stream()
                                .forEach(campaignEligibleService -> {
                                    CampaignJob jobResponse = nTransactionRedeemExecutor.processCampaignWithProduct(campaign, campaignEligibleService);
                                });
                    } else {
                        CampaignJob jobResponse = nTransactionRedeemExecutor.processCampaignWithoutProduct(campaign);
                    }
                }
            } else {
                ThreadUtil.sleepForSecond(60);
            }
        }
    }

    private List<Campaign> getActiveCampaign(List<Campaign> campaigns) {
        return campaigns.stream()
                .filter(f -> (getCampaign(f)) == true).collect(Collectors.toList());
    }

    private boolean getCampaign(Campaign campaign) {
        boolean isWithinTime = DateHelper.checkDateTime(campaign.getStartDate(), campaign.getEndDate());
        return isWithinTime;

    }

}
