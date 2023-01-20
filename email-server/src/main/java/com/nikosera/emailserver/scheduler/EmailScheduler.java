package com.nikosera.emailserver.scheduler;

import com.nikosera.emailserver.property.EmailProperties;
import com.nikosera.emailserver.service.EmailToSendService;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import static com.nikosera.emailserver.constant.SchedulerConstant.EMAIL_SCHEDULER_TIME;
import static com.nikosera.emailserver.constant.StringConstants.YES;

/**
 * @author Sauravi Thapa ON 2/15/21
 */
@Configuration
@EnableScheduling
public class EmailScheduler {

    private final EmailToSendService emailToSendService;

    private final EmailProperties emailProperties;

    public EmailScheduler(EmailToSendService emailToSendService,
                          EmailProperties emailProperties) {
        this.emailToSendService = emailToSendService;
        this.emailProperties = emailProperties;
    }

    /*
    Scheduler to send email from notification/notification parameter table
    If email.enabled='Y' in application.yml file
      email scheduler logic is ON
    else
     email scheduler logic is OFF
     */
    @Scheduled(fixedDelayString = EMAIL_SCHEDULER_TIME)
    public void sendEmail() {
        if (emailProperties.getEnabled().equals(YES)) {
            emailToSendService.sendEmail();
        }
    }
}
