package com.f1soft.campaign.common.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BaseResponse extends ModelBase {

    public boolean success;
    public String code;
    public String message;
    public String unicodeMessage;
    public String paidFor;

}
