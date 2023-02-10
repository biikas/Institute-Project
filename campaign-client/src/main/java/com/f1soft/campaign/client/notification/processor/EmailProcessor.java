package com.f1soft.campaign.client.notification.processor;

import com.f1soft.campaign.client.dto.EmailDynamicParamDTO;
import com.f1soft.campaign.client.notification.composer.EmailComposer;
import com.f1soft.campaign.client.notification.email.EmailOperationContext;
import com.f1soft.campaign.common.dto.HttpJobResponse;
import com.f1soft.campaign.common.dto.JobResponse;
import com.f1soft.campaign.entities.model.EmailDynamicParameter;
import com.f1soft.campaign.entities.model.EmailParameter;
import com.f1soft.campaign.entities.model.EmailToSend;
import com.f1soft.campaign.repository.EmailToSendRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

/**
 * @Author Nitesh Poudel
 */
@Slf4j
@Component
public class EmailProcessor {

    @Autowired
    private EmailComposer emailComposer;
    @Autowired
    private EmailOperationContext emailOperationContext;
    @Autowired
    private EmailToSendRepository emailToSendRepository;

    public Future<JobResponse> process(EmailToSend emailToSend) {

        try {
            Map<String, Object> parameterMap = getParameterMap(emailToSend);

            String bodyMessage = emailComposer.composeHtmlContent(emailToSend.getTemplateName(), parameterMap);

            if (bodyMessage == null) {
                log.error("Email content blank.");
                return new AsyncResult(new JobResponse(false, emailToSend.getId(), "Email content blank"));
            }

            HttpJobResponse httpJobResponse = emailOperationContext.sendMail(emailToSend.getReceiverAddress(), emailToSend.getSubject(), bodyMessage);
            if (httpJobResponse.isSuccess()) {
                log.info("Email sent success for  id : {} ", emailToSend.getId());
                emailToSend.setIsSent('Y');
                emailToSendRepository.save(emailToSend);
                return new AsyncResult(new JobResponse(true, emailToSend.getId(), "Successfully sent"));
            } else {
                log.error("Email sent failure for id  : {}", emailToSend.getId());
                return new AsyncResult(new JobResponse(false, emailToSend.getId(), httpJobResponse.getMessage()));
            }

        } catch (Exception e) {
            log.error("Exception : ", e);
            return new AsyncResult(new JobResponse(false, emailToSend.getId(), e.getMessage()));
        }
    }

    private Map<String, Object> getParameterMap(EmailToSend emailToSend) {
        List<EmailParameter> emailParameterList = emailToSend.getEmailParametersList();
        Map<String, Object> parameterMap = new HashMap<>();
        emailParameterList
                .stream()
                .forEach((emailParameter) -> {
                    parameterMap.put(emailParameter.getParamKey(), emailParameter.getParamValue());
                });

        if (emailToSend.getEmailDynamicParameterList() != null && !emailToSend.getEmailDynamicParameterList().isEmpty()) {
            List<EmailDynamicParamDTO> emailDynamicParamDTOS = new ArrayList<>();

            List<EmailDynamicParameter> emailDynamicParameterList = emailToSend.getEmailDynamicParameterList();
            emailDynamicParameterList
                    .stream()
                    .map(dynamicParameter -> {
                        EmailDynamicParamDTO emailDynamicParamDTO = new EmailDynamicParamDTO();
                        emailDynamicParamDTO.setParamKey(dynamicParameter.getParamKey());
                        emailDynamicParamDTO.setParamValue(dynamicParameter.getParamValue());
                        return emailDynamicParamDTO;
                    }).collect(Collectors.toList())
                    .forEach(emailDynamicParam -> {
                        emailDynamicParamDTOS.add(emailDynamicParam);
                    });

            parameterMap.put("dynamicParam", emailDynamicParamDTOS);

        }
        return parameterMap;
    }
}
