package com.f1soft.campaign.web.crud.service;

import com.f1soft.campaign.entities.model.Service;

public class ServiceCrudMapper {

    public static Service convertToCreateService(ServiceRequest serviceRequest) {
        Service service = new Service();
        service.setActive('Y');
        service.setCode(serviceRequest.getCode());
        service.setName(serviceRequest.getName());
        service.setModule(serviceRequest.getModule());
        return service;
    }

    public static ServiceDTO convertToServiceResponse(Service service) {
        ServiceDTO serviceDTO = new ServiceDTO();
        serviceDTO.setId(service.getId());
        serviceDTO.setActive(service.getActive());
        serviceDTO.setCode(service.getCode());
        serviceDTO.setName(service.getName());
        serviceDTO.setModule(service.getModule());
        return serviceDTO;
    }

    public static Service convertToModifyService(Service service, ServiceRequest serviceRequest) {
        service.setActive(serviceRequest.getActive());
        service.setCode(serviceRequest.getCode());
        service.setName(serviceRequest.getName());
        service.setModule(serviceRequest.getModule());
        return service;
    }
}
