package com.f1soft.campaign.common.dto;

import com.f1soft.campaign.common.util.ApiResponseHelper;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GenericResponse extends ApiResponseHelper {

    private boolean success;
    private String message;
    private String code;
    private String processingCode;
    private Object data;
}
