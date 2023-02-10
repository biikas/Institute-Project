package com.f1soft.campaign.client.notification.email;


import com.f1soft.campaign.common.dto.HttpJobResponse;

/**
 * @author Rashim Dhaubanjar
 */

public interface EmailOperation {

    HttpJobResponse sendEmail(String recipient, String subject, String bodyMessage);

}
