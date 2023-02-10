package com.f1soft.campaign.web.task.registeredCustomer;

import com.f1soft.campaign.common.cbs.dto.CampaignJob;
import com.f1soft.campaign.common.enums.EventTypeEnum;
import com.f1soft.campaign.common.util.DateHelper;
import com.f1soft.campaign.common.util.ThreadUtil;
import com.f1soft.campaign.entities.model.Campaign;
import com.f1soft.campaign.repository.CampaignRepository;
import com.f1soft.campaign.repository.RegistrationLogTrackerRepository;
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
public class EligibleRegisteredCustomerTask {

    @Autowired
    private CampaignRepository campaignRepository;
    @Autowired
    private RegistrationLogTrackerRepository registrationLogTrackerRepository;

    @Autowired
    private EligibleRegisteredCustomerExecutor eligibleRegisteredCustomerExecutor;

    public void call() {
        while (true) {
            List<Campaign> campaigns = campaignRepository.getCampaignByEventType(EventTypeEnum.REGISTRATION.name());
            List<Campaign> campaignList = getActiveCampaign(campaigns);

            log.debug("Active Campaign list size : {}", campaignList.size());
            if (campaignList.size() > 0) {
                campaigns.stream()
                        .forEach(campaign -> {
                            CampaignJob jobResponse = eligibleRegisteredCustomerExecutor.processFetchCustomer(campaign);
                        });

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
