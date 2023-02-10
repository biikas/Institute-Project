package com.f1soft.campaign.client.event;

import com.f1soft.campaign.client.dto.EmailMessage;
import org.springframework.context.ApplicationEvent;

import java.util.List;

public class EmailEvent extends ApplicationEvent {

    public EmailEvent(List<EmailMessage> emailMessageList) {
        super(emailMessageList);
    }

}
