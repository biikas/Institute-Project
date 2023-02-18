package com.college.campaign.client.notification.email;

import com.college.campaign.common.dto.HttpJobResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Qualifier("local")
public class LocalEmailSender implements EmailOperation {

    @Override
    public HttpJobResponse sendEmail(String recipient, String subject, String bodyMessage) {
        HttpJobResponse jobResponse = new HttpJobResponse();
        jobResponse.setMessage("Successfully sent");
        jobResponse.setSuccess(true);
        return jobResponse;
    }
}
