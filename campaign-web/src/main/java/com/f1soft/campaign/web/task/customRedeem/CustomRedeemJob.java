package com.f1soft.campaign.web.task.customRedeem;

import com.f1soft.campaign.common.config.application.SystemConfig;
import com.f1soft.campaign.web.constant.RunnerConfigConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Shreetika Panta
 */

@Component
public class CustomRedeemJob {

    @Autowired
    private SystemConfig systemConfig;
    @Autowired
    private CustomRedeemTask customRedeemTask;

    public void job() {
        boolean runCustomRedeemProcessor = systemConfig.runnerConfig(RunnerConfigConstant.ENABLE_CUSTOM_REDEEM_PROCESSOR).equalsIgnoreCase("Y");

        if (runCustomRedeemProcessor) {
            customRedeemTask.call();
        }
    }
}
