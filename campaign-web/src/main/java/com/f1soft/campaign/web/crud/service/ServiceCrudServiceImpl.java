package com.f1soft.campaign.web.crud.service;

import com.f1soft.campaign.common.constant.MsgConstant;
import com.f1soft.campaign.common.dto.ServerResponse;
import com.f1soft.campaign.common.util.ResponseMsg;
import com.f1soft.campaign.repository.ServiceRepository;
import com.f1soft.campaign.web.dto.request.StatusRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ServiceCrudServiceImpl implements ServiceCrudService {

    @Autowired
    private ServiceRepository serviceRepository;
    @Autowired
    private ServiceCrudManager serviceCrudManager;

    @Override
    public ServerResponse getService() {
        List<ServiceDTO> serviceDTOS = serviceRepository.findAll()
                .stream()
                .filter(f-> f.getActive() == 'Y')
                .map(service -> ServiceCrudMapper.convertToServiceResponse(service))
                .collect(Collectors.toList());

        ServiceResponse serviceResponse = new ServiceResponse();
        serviceResponse.setServiceList(serviceDTOS);
        return ResponseMsg.successResponse(MsgConstant.Data.SUCCESS, serviceResponse);
    }

    @Override
    public ServerResponse getServiceById(Long id) {
        com.f1soft.campaign.entities.model.Service service = serviceCrudManager.findById(id);
        return serviceResponse(service);
    }

    @Override
    public ServerResponse modifyService(Long id, ServiceRequest serviceRequest) {
        com.f1soft.campaign.entities.model.Service serviceEntity = serviceCrudManager.findById(id);

        serviceCrudManager.checkItAlreadyExists(id, serviceRequest);

        com.f1soft.campaign.entities.model.Service service = ServiceCrudMapper.convertToModifyService(serviceEntity, serviceRequest);
        serviceCrudManager.save(service);

        return serviceResponse(service);
    }

    @Override
    public ServerResponse createService(ServiceRequest serviceRequest) {
        serviceCrudManager.checkItAlreadyExists(null, serviceRequest);

        com.f1soft.campaign.entities.model.Service service = ServiceCrudMapper.convertToCreateService(serviceRequest);

        serviceRepository.save(service);
        return serviceResponse(service);
    }

    @Override
    public ServerResponse modifyStatus(Long id, StatusRequest statusRequest) {
        com.f1soft.campaign.entities.model.Service service = serviceCrudManager.findById(id);
        service.setActive(statusRequest.getActive());

        serviceCrudManager.save(service);
        return serviceResponse(service);
    }

    private ServerResponse serviceResponse(com.f1soft.campaign.entities.model.Service service) {
        ServiceDTO serviceDTO = ServiceCrudMapper.convertToServiceResponse(service);
        return ResponseMsg.successResponse(MsgConstant.Data.SUCCESS, serviceDTO);
    }
}
