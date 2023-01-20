package com.nikosera.emailserver.utils;

import com.nikosera.emailserver.constant.NotificationConstant;
import com.nikosera.entity.Notification;

import java.util.Date;
import java.util.function.Consumer;

/**
 * @author Sauravi Thapa ON 2/15/21
 */
public class NotificationUtils {

    public static Consumer<Notification> updateNotification=(notification)->{
        notification.setStatus(NotificationConstant.Status.SENT);
        notification.setSentDate(new Date());
    };
}
