package com.f1soft.campaign.web.task.nthTransactionRedeem;

import com.f1soft.campaign.common.config.application.SystemConfig;
import com.f1soft.campaign.web.constant.RunnerConfigConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Shreetika Panta
 */

@Component
public class NTransactionRedeemJob {

    @Autowired
    private SystemConfig systemConfig;
    @Autowired
    private NTransactionRedeemTask nTransactionRedeemTask;

    public void job() {
        boolean runEligibleCustomerCampaignProcessor = systemConfig.runnerConfig(RunnerConfigConstant.ENABLE_REDEEM_NTH_TRANSACTION).equalsIgnoreCase("Y");

        if (runEligibleCustomerCampaignProcessor) {
            nTransactionRedeemTask.call();
        }
    }
}
