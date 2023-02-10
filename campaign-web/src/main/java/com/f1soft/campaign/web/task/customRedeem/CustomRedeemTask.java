package com.f1soft.campaign.web.task.customRedeem;

import com.f1soft.campaign.common.cbs.dto.CampaignJob;
import com.f1soft.campaign.common.enums.EventTypeEnum;
import com.f1soft.campaign.common.util.DateHelper;
import com.f1soft.campaign.common.util.ThreadUtil;
import com.f1soft.campaign.entities.model.Campaign;
import com.f1soft.campaign.repository.CampaignRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Shreetika Panta
 */

@Component
public class CustomRedeemTask {

    @Autowired
    private CampaignRepository campaignRepository;
    @Autowired
    private CustomRedeemExecutor customRedeemExecutor;

    public void call() {
        List<Campaign> campaigns = campaignRepository.getCampaignByEventType(EventTypeEnum.CUSTOM.name());

        List<Campaign> campaignList = getActiveCampaign(campaigns);

        if (campaignList.size() > 0) {
            campaigns.stream()
                    .forEach(campaign -> {
                        CampaignJob jobResponse = customRedeemExecutor.processFetchData(campaign);
                    });

        } else {
            ThreadUtil.sleepForSecond(60);
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
