package com.f1soft.campaign.client.service.impl;

import com.f1soft.campaign.client.dto.EmailMessage;
import com.f1soft.campaign.client.manager.EventManager;
import com.f1soft.campaign.client.service.EmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class EmailServiceImpl implements EmailService {

    private final ApplicationEventPublisher eventPublisher;

    public EmailServiceImpl(ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    @Override
    public void email(List<EmailMessage> emailMessageList) {
        this.eventPublisher.publishEvent(EventManager.getEmailEvent(emailMessageList));
    }
}
