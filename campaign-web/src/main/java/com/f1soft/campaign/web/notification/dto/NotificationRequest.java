package com.f1soft.campaign.web.notification.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@ToString
public class NotificationRequest {
    @NotNull
    private Long campaignId;
    @NotEmpty
    private String title;
    @NotEmpty
    private String description;
    @NotEmpty
    private String shortDescription;
    private char appendBanner;
    private char test;
    private List<String> testNumber;
}
