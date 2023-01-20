package com.nikosera.notification.service.impl;

import com.nikosera.entity.Notification;
import com.nikosera.notification.builder.NotificationBuilder;
import com.nikosera.notification.dto.EmailMessage;
import com.nikosera.notification.service.NotificationService;
import com.nikosera.repository.repository.core.NotificationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Narayan Joshi
 * @email narenzoshi@gmail.com
 */

@Slf4j
@Service
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;

    public NotificationServiceImpl(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    @Override
    public void email(List<EmailMessage> emailMessageList) {

        log.debug("Email message List : {}", emailMessageList);

        emailMessageList.stream()
                .forEach(message -> {
                    Notification notification = NotificationBuilder.convertToNotification(message);

                    saveNotification(notification);
                });
    }

    private void saveNotification(Notification notification) {
        notificationRepository.save(notification);
    }

}
