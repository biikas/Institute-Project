package com.f1soft.campaign.transaction.connector.transaction.topup;


import com.f1soft.campaign.common.config.application.SystemConfig;
import com.f1soft.campaign.common.constant.AppConfigConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * @author Bikash Shah
 */
@Slf4j
@Component
public class TopUpFactory {

    @Autowired
    private SystemConfig systemConfig;
    //
    @Autowired
    @Qualifier("live")
    private TopupOperations liveTopupOperations;

    @Autowired
    @Qualifier("local")
    private TopupOperations localTopupOperations;

    public TopupOperations getTopupOperationsMode() {
        String mode = systemConfig.appConfig(AppConfigConstant.TOPUP_MODE);
        if (mode.equalsIgnoreCase("live")) {
            return liveTopupOperations;
        } else if (mode.equalsIgnoreCase("local")) {
            return localTopupOperations;
        }
        throw new IllegalStateException();
    }

}


