package com.college.campaign.web.crud.service;

import com.college.campaign.common.dto.ModelBase;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ServiceResponse extends ModelBase {

    private List<ServiceDTO> serviceList;
}
