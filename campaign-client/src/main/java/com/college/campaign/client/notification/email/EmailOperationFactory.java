package com.college.campaign.client.notification.email;

import com.college.campaign.common.config.application.SystemConfig;
import com.college.campaign.common.config.constant.AppConfigConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * @author Rashim Dhaubanjar
 */

@Component
public class EmailOperationFactory {

    @Autowired
    private SystemConfig systemConfig;

    @Autowired
    @Qualifier("live")
    private EmailOperation emailSender;

    @Autowired
    @Qualifier("local")
    private EmailOperation localEmailSender;

    public EmailOperation getEmailOperation() {
        if (systemConfig.appConfig(AppConfigConstant.EMAIL_SEND_MODE).equalsIgnoreCase("live")) {
            return emailSender;
        } else if (systemConfig.appConfig(AppConfigConstant.EMAIL_SEND_MODE).equalsIgnoreCase("local")) {
            return localEmailSender;
        }
        return null;
    }
}
