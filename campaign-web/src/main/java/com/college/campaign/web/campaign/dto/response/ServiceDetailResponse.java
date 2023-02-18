package com.college.campaign.web.campaign.dto.response;

import com.college.campaign.common.dto.ModelBase;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ServiceDetailResponse extends ModelBase {

    private Long id;
    private String code;
    private String name;
    private String module;
    private char active;
}
