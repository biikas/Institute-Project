package com.nikosera.emailserver.service.impl;

import com.nikosera.common.aop.MethodLogging;
import com.nikosera.common.exception.NoResultFoundException;
import com.nikosera.emailserver.property.EmailProperties;
import com.nikosera.emailserver.service.EmailToSendService;
import com.nikosera.emailserver.utils.FileResourceUtils;
import com.nikosera.emailserver.constant.NotificationConstant;
import com.nikosera.entity.Notification;
import com.nikosera.entity.NotificationParameter;
import com.nikosera.repository.repository.core.NotificationParameterRepository;
import com.nikosera.repository.repository.core.NotificationRepository;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static com.nikosera.emailserver.constant.EmailConstants.LOGO_FILE_NAME;
import static com.nikosera.emailserver.constant.EmailConstants.LOGO_LOCATION;
import static com.nikosera.emailserver.utils.NotificationUtils.updateNotification;
import static javax.mail.Message.RecipientType.TO;
import static org.springframework.ui.freemarker.FreeMarkerTemplateUtils.processTemplateIntoString;

/**
 * @author Sauravi Thapa ON 2/15/21
 */
@Slf4j
@Transactional
@Service
public class EmailToSendServiceImpl implements EmailToSendService {

    private final Configuration configuration;

    private final EmailProperties emailProperties;

    private final JavaMailSender javaMailSender;

    private final NotificationRepository notificationRepository;

    private final NotificationParameterRepository notificationParameterRepository;

//    private final BCryptPasswordEncoder passwordEncoder;

    public EmailToSendServiceImpl(Configuration configuration,
                                  EmailProperties emailProperties,
                                  @Qualifier("getMailSender") JavaMailSender javaMailSender,
                                  NotificationRepository notificationRepository,
                                  NotificationParameterRepository notificationParameterRepository) {
        this.configuration = configuration;
        this.emailProperties = emailProperties;
        this.javaMailSender = javaMailSender;
        this.notificationRepository = notificationRepository;
        this.notificationParameterRepository = notificationParameterRepository;
    }

    @MethodLogging
    @Override
    public void sendEmail() {
        List<Notification> notificationList = fetchEmailList(NotificationConstant.Status.PENDING, NotificationConstant.Type.EMAIL);
        notificationList.forEach(notification -> {
            List<NotificationParameter> notificationParameters = fetchNotificationParameterList(notification.getId());
            send(notification,notificationParameters);
            updateNotification.accept(notification);
//            updatePassword(notificationParameters);
        });
    }

    private void send(Notification notification,List<NotificationParameter> notificationParameters) {

        try {

            MimeMessage message = getMimeMessage(notification);

            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            Map<String, Object> model = new HashMap<>();

            String html = "";

            parseParamValues(notificationParameters, model);

            switch (notification.getTemplateName()) {

                case NotificationConstant.Template.USER_REGISTRATION:
                    html = getFreeMarkerContent(model, com.nikosera.emailserver.constant.Template.USER_REGISTRATION_TEMPLATE, html);
                    break;

                case NotificationConstant.Template.CHANGE_PASSWORD:
                    html = getFreeMarkerContent(model, com.nikosera.emailserver.constant.Template.CHANGE_PASSWORD_TEMPLATE, html);
                    break;

            }

            helper.setText(html, true);

            helper.setFrom(emailProperties.getUsername());

            helper.addInline(LOGO_FILE_NAME, new FileSystemResource
                    (new FileResourceUtils().convertResourcesFileIntoFile(LOGO_LOCATION)));

            javaMailSender.send(message);

        } catch (MessagingException e) {

            e.printStackTrace();
        }
    }

    private MimeMessage getMimeMessage(Notification notification) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        message.setRecipient(TO, new InternetAddress(notification.getNotifiedTo()));
        message.setSubject(notification.getSubject());
        return message;
    }

    private String getFreeMarkerContent(Map<String, Object> model, String templateName, String html) {
        try {
            Template template = configuration.getTemplate(templateName);
            html = processTemplateIntoString(template, model);
        } catch (IOException | TemplateException e) {
            e.printStackTrace();
        }
        return html;
    }

    private void parseParamValues(List<NotificationParameter> notificationParametersList,
                                  Map<String, Object> model) {
        notificationParametersList.forEach(notificationParameter -> {
            model.put(notificationParameter.getParamKey(), notificationParameter.getParamValue());
        });
    }


    private List<Notification> fetchEmailList(String status, String type) {
        return notificationRepository.fetchNotificationToSend(status, type);
    }

    private List<NotificationParameter> fetchNotificationParameterList(Long id) {

        List<NotificationParameter> notificationParameters = notificationParameterRepository.fetchByNotificationId(id);

        if (notificationParameters.isEmpty()) {
            throw NOTIFICATION_PARAMETER_NOT_FOUND.apply(id);
        }

        return notificationParameters;
    }

    private Function<Long, NoResultFoundException> NOTIFICATION_PARAMETER_NOT_FOUND = (notificationId) -> {
        throw new NoResultFoundException( "NOTIFICATION PARAMETER NOT " +
                "FOUND WITH NOTIFICATION ID : " + notificationId);
    };

    private Function<Long, NoResultFoundException> NOTIFICATION_NOT_FOUND = (notificationId) -> {
        throw new NoResultFoundException("NOTIFICATION NOT FOUND WITH ID : " + notificationId);
    };

//    private void updatePassword(List<NotificationParameter> notificationParameters) {
//
//
//        notificationParameters.forEach(notificationParameter -> {
//            if(notificationParameter.getParamKey().equals("password")){
//                notificationParameter.setParamValue(passwordEncoder
//                        .encode(notificationParameter.getParamValue()));
//            }
//        });
//
//    }

}
