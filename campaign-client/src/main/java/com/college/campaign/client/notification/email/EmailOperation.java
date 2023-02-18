package com.college.campaign.client.notification.email;


import com.college.campaign.common.dto.HttpJobResponse;

/**
 * @author Rashim Dhaubanjar
 */

public interface EmailOperation {

    HttpJobResponse sendEmail(String recipient, String subject, String bodyMessage);

}
