package com.f1soft.campaign.web.crud.service;

import com.f1soft.campaign.common.dto.ModelBase;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ServiceResponse extends ModelBase {

    private List<ServiceDTO> serviceList;
}
