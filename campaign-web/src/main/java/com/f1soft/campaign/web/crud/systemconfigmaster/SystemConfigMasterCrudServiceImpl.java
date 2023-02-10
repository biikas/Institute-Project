package com.f1soft.campaign.web.crud.systemconfigmaster;

import com.f1soft.campaign.common.constant.MsgConstant;
import com.f1soft.campaign.common.dto.ServerResponse;
import com.f1soft.campaign.common.util.ResponseMsg;
import com.f1soft.campaign.entities.model.SystemConfigMaster;
import com.f1soft.campaign.repository.SystemConfigMasterRepository;
import com.f1soft.campaign.web.dto.request.StatusRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class SystemConfigMasterCrudServiceImpl implements SystemConfigMasterCrudService {

    @Autowired
    private SystemConfigMasterRepository systemConfigMasterRepository;
    @Autowired
    private SystemConfigMasterManager systemConfigMasterManager;

    @Override
    public ServerResponse createSystemConfigMaster(SystemConfigMasterRequest systemConfigMasterRequest) {
        systemConfigMasterManager.checkItAlreadyExists(null, systemConfigMasterRequest);
        SystemConfigMaster systemConfigMaster = SystemConfigMasterMapper.convertToCreateSystemConfigMaster(systemConfigMasterRequest);
        systemConfigMasterManager.save(systemConfigMaster);
        return systemConfigMasterResponse(systemConfigMaster);
    }


    @Override
    public ServerResponse getSystemConfigMaster() {
        List<SystemConfigMasterDTO> systemConfigMasterDTOS = systemConfigMasterRepository.findAll()
                .stream()
                .map(systemConfigMaster -> SystemConfigMasterMapper.convertToSystemConfigmasterDTO(systemConfigMaster))
                .collect(Collectors.toList());

        SystemConfigMasterResponse systemConfigMasterResponse = new SystemConfigMasterResponse();
        systemConfigMasterResponse.setSystemConfigMaster(systemConfigMasterDTOS);

        return ResponseMsg.successResponse(MsgConstant.Data.SUCCESS, systemConfigMasterResponse);
    }

    @Override
    public ServerResponse getSystemConfigMasterById(Integer id) {
        SystemConfigMaster systemConfigMaster = systemConfigMasterManager.findById(id);
        return systemConfigMasterResponse(systemConfigMaster);
    }

    @Override
    public ServerResponse modifySystemConfigMaster(Integer id, SystemConfigMasterRequest systemConfigMasterRequest) {
        SystemConfigMaster systemConfigMasterEntity = systemConfigMasterManager.findById(id);
        SystemConfigMaster systemConfigMaster = SystemConfigMasterMapper.convertToModifySystemConfigMaster(systemConfigMasterEntity, systemConfigMasterRequest);
        systemConfigMasterManager.checkItAlreadyExists(id, systemConfigMasterRequest);

        systemConfigMasterManager.save(systemConfigMaster);
        return systemConfigMasterResponse(systemConfigMaster);
    }

    @Override
    public ServerResponse modifyStatus(Integer id, StatusRequest statusRequest) {
        SystemConfigMaster systemConfigMasterEntity = systemConfigMasterManager.findById(id);
        systemConfigMasterEntity.setActive(statusRequest.getActive());
        systemConfigMasterManager.save(systemConfigMasterEntity);
        return systemConfigMasterResponse(systemConfigMasterEntity);
    }

    private ServerResponse systemConfigMasterResponse(SystemConfigMaster systemConfigMaster) {
        SystemConfigMasterDTO systemConfigMasterDTO = SystemConfigMasterMapper.convertToSystemConfigmasterDTO(systemConfigMaster);
        return ResponseMsg.successResponse(MsgConstant.Data.SUCCESS, systemConfigMasterDTO);
    }

}
