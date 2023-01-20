package com.nikosera.notification.utils;

import com.nikosera.entity.Notification;

import java.util.Date;
import java.util.function.Consumer;

/**
 * @author Sauravi Thapa ON 2/17/21
 */
public class NotificationUtils {

    public static Consumer<Notification> updateNotification=(notification)->{
        notification.setSentDate(new Date());
    };
}
