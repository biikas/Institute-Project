package com.f1soft.campaign.client.manager;

import com.f1soft.campaign.client.event.EmailEvent;
import com.f1soft.campaign.client.dto.EmailMessage;

import java.util.List;

/**
 * @Author Nitesh Poudel
 */
public class EventManager {

    public static EmailEvent getEmailEvent(List<EmailMessage> emailMessageList) {
        return new EmailEvent(emailMessageList);
    }

}
