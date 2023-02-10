package com.f1soft.campaign.client.mapper;

import com.f1soft.campaign.entities.model.EmailDynamicParameter;
import com.f1soft.campaign.entities.model.EmailParameter;
import com.f1soft.campaign.entities.model.EmailToSend;
import com.f1soft.campaign.client.dto.EmailMessage;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Author Nitesh Poudel
 */
public class EmailMapper {

    public static EmailToSend convertToEmailToSend(EmailMessage message, String address) {
        EmailToSend emailToSend = new EmailToSend();
        emailToSend.setIsSent('N');
        emailToSend.setRecordedDate(new Date());
        emailToSend.setTemplateName(message.getTemplate());
        emailToSend.setSubject(message.getSubject());
        emailToSend.setReceiverAddress(address);

        List<EmailParameter> emailParameterList = new ArrayList<>();

        for (Map.Entry<String, String> map : message.getParamMap().entrySet()) {
            EmailParameter emailParameter = new EmailParameter();
            emailParameter.setParamKey(map.getKey());
            emailParameter.setParamValue(map.getValue());
            emailParameter.setEmailToSend(emailToSend);
            emailParameterList.add(emailParameter);
        }

        emailToSend.setEmailParametersList(emailParameterList);

        List<EmailDynamicParameter> emailDynamicParameterList = new ArrayList<>();
        if (message.getDynamicParamMap() != null && !message.getDynamicParamMap().isEmpty()) {
            for (Map.Entry<String, String> map : message.getDynamicParamMap().entrySet()) {
                EmailDynamicParameter emailParameter = new EmailDynamicParameter();
                emailParameter.setParamKey(map.getKey());
                emailParameter.setParamValue(map.getValue());
                emailParameter.setEmailToSend(emailToSend);
                emailDynamicParameterList.add(emailParameter);
            }
            emailToSend.setEmailDynamicParameterList(emailDynamicParameterList);
        }

        return emailToSend;
    }
}
