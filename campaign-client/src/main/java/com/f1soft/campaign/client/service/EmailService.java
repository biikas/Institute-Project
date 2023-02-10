package com.f1soft.campaign.client.service;

import com.f1soft.campaign.client.dto.EmailMessage;

import java.util.List;

public interface EmailService {

    void email(List<EmailMessage> emailMessageList);
}
