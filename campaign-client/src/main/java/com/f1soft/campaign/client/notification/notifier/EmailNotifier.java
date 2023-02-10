package com.f1soft.campaign.client.notification.notifier;

import com.f1soft.campaign.client.dto.EmailMessage;
import com.f1soft.campaign.client.mapper.EmailMapper;
import com.f1soft.campaign.client.notification.processor.EmailProcessor;
import com.f1soft.campaign.entities.model.EmailToSend;
import com.f1soft.campaign.repository.EmailToSendRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationEvent;
import org.springframework.stereotype.Service;

import java.util.List;

/*
 * @Author Rashim Dhaubanjar
 */

@Slf4j
@Service
@Qualifier("emailNotifier")
public class EmailNotifier implements Notifier {

    @Autowired
    private EmailProcessor emailProcessor;
    @Autowired
    private EmailToSendRepository emailToSendRepository;

    @Override
    public void notify(ApplicationEvent event) {

        log.debug("Notification through email");
        List<EmailMessage> emailMessageList = (List<EmailMessage>) event.getSource();

        log.debug("Email message List : {}", emailMessageList);

        emailMessageList.stream()
                .forEach(message -> {
                    for (String address : message.getAddressList()) {
                        EmailToSend emailToSend = EmailMapper.convertToEmailToSend(message, address);
                        emailToSendRepository.save(emailToSend);
                        emailProcessor.process(emailToSend);
                    }
                });
    }
}