package com.college.campaign.client.notification.notifier;

import org.springframework.context.ApplicationEvent;

public interface Notifier {

    void notify(ApplicationEvent event);

}
