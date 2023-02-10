package com.f1soft.campaign.web.task.registeredCustomer;

import com.f1soft.campaign.common.config.application.SystemConfig;
import com.f1soft.campaign.web.constant.RunnerConfigConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Shreetika Panta
 */

@Slf4j
@Component
public class EligibleRegisteredCustomerJob {

    @Autowired
    private SystemConfig systemConfig;
    @Autowired
    private EligibleRegisteredCustomerTask eligibleRegisteredCustomerTask;

    public void job() {
        boolean runEligibleCustomerCampaignProcessor = systemConfig.runnerConfig(RunnerConfigConstant.ENABLE_FETCH_REGISTERED_CUSTOMER_PROCESSOR).equalsIgnoreCase("Y");

        if (runEligibleCustomerCampaignProcessor) {
            eligibleRegisteredCustomerTask.call();
        }
    }
}
