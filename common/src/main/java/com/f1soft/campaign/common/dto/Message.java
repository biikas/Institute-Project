package com.f1soft.campaign.common.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Message extends ModelBase {

    private String message;
    private String reportMessage;
    private Long messageId;
    private Long messageTypeId;
    private Long messageFormatId;
    private Integer messageFlag;
    private String username;
    private Long channelProviderId;
    private String encryptionIndex;
    private String encryptedMessage;
    private boolean isEncrypted = false;
    private String txnRefId;
}
