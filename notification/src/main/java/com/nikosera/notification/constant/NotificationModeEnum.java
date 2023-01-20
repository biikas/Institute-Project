package com.nikosera.notification.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/*
 * @Author Rashim Dhaubanjar
 */

@Getter
@AllArgsConstructor
public enum NotificationModeEnum {
    EMAIL("EMAIL");

    private String mode;

}
