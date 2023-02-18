package com.college.campaign.common.dto;

import com.college.campaign.common.util.ApiResponseHelper;
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
