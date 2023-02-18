package com.college.campaign.client.notification.email;

import com.college.campaign.client.connector.EmailServerConnector;
import com.college.campaign.common.dto.HttpJobResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;

import javax.json.Json;
import javax.json.JsonObject;

@Slf4j
@Component
@Qualifier("live")
public class EmailSender implements EmailOperation {

    private static final String EMAIL_SERVER_PATH = "/email/send";

    @Autowired
    private EmailServerConnector emailServerConnector;

    @Override
    public HttpJobResponse sendEmail(String recipient, String subject, String bodyMessage) {
        JsonObject jsonObject = Json.createObjectBuilder()
                .add("recipient", recipient)
                .add("subject", subject)
                .add("bodyMessage", bodyMessage)
                .build();

        return emailServerConnector.request(
                EMAIL_SERVER_PATH,
                jsonObject.toString(),
                new ParameterizedTypeReference<HttpJobResponse>() {
                });
    }

}
