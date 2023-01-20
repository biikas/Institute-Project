package com.nikosera.user.invoker;

import com.nikosera.entity.ApplicationUser;
import com.nikosera.notification.constant.NotificationConstant;
import com.nikosera.notification.dto.EmailMessage;
import com.nikosera.notification.service.NotificationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Narayan Joshi <narenzoshi@gmail.com>
 */
@Slf4j
@Component
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class NotificationInvoker {

    private final NotificationService notificationService;

    public void createUserNotification(ApplicationUser user, String password) {
        List<EmailMessage> emailMessages = new ArrayList<>();
        Map<String, String> parameterMap = new HashMap<>();

        parameterMap.put("name", user.getName());
        parameterMap.put("username", user.getUsername());
        parameterMap.put("password", password);

        EmailMessage emailMessage = new EmailMessage(NotificationConstant.Template.USER_REGISTRATION,
                NotificationConstant.Subject.USER_REGISTRATION,
                parameterMap,
                user.getEmailAddress());

        emailMessages.add(emailMessage);
        notificationService.email(emailMessages);
    }

    public void changePasswordNotification(ApplicationUser user) {
        List<EmailMessage> emailMessages = new ArrayList<>();
        Map<String, String> parameterMap = new HashMap<>();

        parameterMap.put("name", user.getName());

        EmailMessage emailMessage = new EmailMessage(NotificationConstant.Template.CHANGE_PASSWORD,
                NotificationConstant.Subject.CHANGE_PASSWORD,
                parameterMap,
                user.getEmailAddress());

        emailMessages.add(emailMessage);
        notificationService.email(emailMessages);
    }
}
