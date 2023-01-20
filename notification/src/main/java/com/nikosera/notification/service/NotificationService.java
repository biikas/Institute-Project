package com.nikosera.notification.service;

import com.nikosera.notification.dto.EmailMessage;

import java.util.List;

/*
 * @Author Rashim Dhaubanjar
 */


public interface NotificationService {

    void email(List<EmailMessage> emailMessageList);
}



