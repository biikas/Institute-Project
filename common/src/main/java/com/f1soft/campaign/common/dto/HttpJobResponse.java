package com.f1soft.campaign.common.dto;

import com.f1soft.campaign.common.dto.ModelBase;
import lombok.Getter;
import lombok.Setter;

/**
 * @Author Nitesh Poudel
 */

@Getter
@Setter
public class HttpJobResponse extends ModelBase {

    private boolean success;
    private String message;
    private String code;
    private Object obj;
}
