package com.f1soft.campaign.web.notification.dto;

import com.f1soft.campaign.common.dto.ModelBase;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FCMNotificationResponse extends ModelBase {

    private List<String> success;
    private List<String> failure;
}
