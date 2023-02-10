package com.f1soft.campaign.web.crud.service;

import com.f1soft.campaign.common.constant.MsgConstant;
import com.f1soft.campaign.common.exception.DataNotFoundException;
import com.f1soft.campaign.common.exception.ResourceAlreadyExistException;
import com.f1soft.campaign.common.util.MessageComposer;
import com.f1soft.campaign.common.util.ResponseMsg;
import com.f1soft.campaign.entities.model.Service;
import com.f1soft.campaign.repository.ServiceRepository;
import com.f1soft.campaign.web.constant.MsgParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ServiceCrudManager {

    @Autowired
    private ServiceRepository serviceRepository;

    public Service findById(Long id) {
        return serviceRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException(ResponseMsg.failureResponse(MsgConstant.Data.NOT_FOUND)));
    }

    public boolean checkItAlreadyExists(Long id, ServiceRequest serviceRequest) {
        Optional<Service> optService = serviceRepository.getServiceByCode(serviceRequest.getCode());
        if (optService.isPresent()) {
            if (id != null && optService.get().getId() != id) {
                throw new ResourceAlreadyExistException(ResponseMsg.failureResponse(
                        MsgConstant.DynamicData.DUPLICATE,
                        MessageComposer.getParameters(MsgParameter.DUPLICATE_DATA, "Service")));
            }

        }
        return false;
    }

    public boolean save(Service service) {
        serviceRepository.save(service);
        return true;
    }
}
