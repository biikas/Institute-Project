package com.college.campaign.web.crud.service;

import com.college.campaign.common.dto.ModelBase;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ServiceRequest extends ModelBase {

    private String code;
    private String name;
    private String module;
    private char active;
}
