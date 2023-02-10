package com.f1soft.campaign.client.notification.notifier;

import org.springframework.context.ApplicationEvent;

public interface Notifier {

    void notify(ApplicationEvent event);

}
