package com.nikosera.notification.builder;

import com.nikosera.entity.Notification;
import com.nikosera.entity.NotificationParameter;
import com.nikosera.notification.constant.NotificationConstant;
import com.nikosera.notification.dto.EmailMessage;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author Narayan Joshi
 * <narenzoshi@gmail.com>
 */

public class NotificationBuilder {

    public static Notification convertToNotification(EmailMessage message) {
        Notification notification = new Notification();
        notification.setType(NotificationConstant.Type.EMAIL);
        notification.setStatus(NotificationConstant.Status.PENDING);
        notification.setRecordedDate(new Date());
        notification.setNotifiedTo(message.getAddress());
        notification.setSubject(message.getSubject());
        notification.setTemplateName(message.getTemplate());

        List<NotificationParameter> notificationParameterList = new ArrayList<>();

        for (Map.Entry<String, String> map : message.getParamMap().entrySet()) {
            NotificationParameter notificationParameter = new NotificationParameter();
            notificationParameter.setParamKey(map.getKey());
            notificationParameter.setParamValue(map.getValue());
            notificationParameter.setNotification(notification);
            notificationParameterList.add(notificationParameter);
        }
        notification.setNotificationParametersList(notificationParameterList);

        return notification;
    }
}
