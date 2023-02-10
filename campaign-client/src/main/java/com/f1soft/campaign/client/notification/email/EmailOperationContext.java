package com.f1soft.campaign.client.notification.email;

import com.f1soft.campaign.common.dto.HttpJobResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmailOperationContext {

    @Autowired
    private EmailOperationFactory emailOperationFactory;

    public HttpJobResponse sendMail(String recipient, String subject, String bodyMessage) {
        return emailOperationFactory.getEmailOperation().sendEmail(recipient, subject, bodyMessage);
    }
}
