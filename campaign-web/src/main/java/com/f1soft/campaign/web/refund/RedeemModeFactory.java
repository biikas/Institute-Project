package com.f1soft.campaign.web.refund;


import com.f1soft.campaign.common.config.application.SystemConfig;
import com.f1soft.campaign.web.constant.RunnerConfigConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;


@Component
public class RedeemModeFactory {

    @Autowired
    private SystemConfig systemConfig;

    @Autowired
    @Qualifier("live")
    private RedeemOperations redeemOperations;

    @Autowired
    @Qualifier("local")
    private RedeemOperations localCashBackOperations;

    public RedeemOperations getRedeemOperations() {

        if (systemConfig.runnerConfig(RunnerConfigConstant.MODE).equalsIgnoreCase("live")) {
            return redeemOperations;
        } else if (systemConfig.runnerConfig(RunnerConfigConstant.MODE).equalsIgnoreCase("local")) {
            return localCashBackOperations;
        }
        return null;
    }
}
