package com.college.campaign.client.notification.listener;

import com.college.campaign.client.event.EmailEvent;
import com.college.campaign.client.notification.notifier.Notifier;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Slf4j
@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public final class EmailEventListener {

    @Autowired
    @Qualifier("emailNotifier")
    private Notifier emailNotifier;

    @EventListener
    public void handleEmailEvent(@Valid @NotNull final EmailEvent emailEvent) {
        emailNotifier.notify(emailEvent);
    }
}
