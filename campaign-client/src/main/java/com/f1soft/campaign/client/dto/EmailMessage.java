package com.f1soft.campaign.client.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
public class EmailMessage {

    private String template;
    private String subject;
    private Map<String, String> paramMap;
    private List<String> addressList;
    private Map<String, String> dynamicParamMap;

    public EmailMessage() {
    }

    public EmailMessage(String template, String subject, Map<String, String> paramMap, List<String> addressList) {
        this.template = template;
        this.subject = subject;
        this.paramMap = paramMap;
        this.addressList = addressList;
    }
}
