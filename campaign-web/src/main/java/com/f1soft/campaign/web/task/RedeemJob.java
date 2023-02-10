package com.f1soft.campaign.web.task;

import com.f1soft.campaign.common.config.application.SystemConfig;
import com.f1soft.campaign.web.constant.RunnerConfigConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Prajwol Hada
 */
@Slf4j
@Component
public class RedeemJob {

    @Autowired
    private SystemConfig systemConfig;

    @Autowired
    private RedeemTask redeemTask;

    public void job() {
        boolean runRedeemProcessor = systemConfig.runnerConfig(RunnerConfigConstant.ENABLE_REDEEM_PROCESSOR).equalsIgnoreCase("Y");

        if (runRedeemProcessor) {
            redeemTask.call();
        }
    }
}
