package com.f1soft.campaign.web.crud.systemconfig;

import com.f1soft.campaign.common.constant.MsgConstant;
import com.f1soft.campaign.common.dto.ServerResponse;
import com.f1soft.campaign.common.util.ResponseMsg;
import com.f1soft.campaign.entities.model.SystemConfig;
import com.f1soft.campaign.entities.model.SystemConfigMaster;
import com.f1soft.campaign.repository.SystemConfigRepository;
import com.f1soft.campaign.web.crud.systemconfigmaster.SystemConfigMasterManager;
import com.f1soft.campaign.web.dto.request.StatusRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@Slf4j
public class SystemConfigCrudServiceImpl implements SystemConfigCrudService {

    @Autowired
    private SystemConfigRepository systemConfigRepository;
    @Autowired
    private SystemConfigManager systemConfigManager;
    @Autowired
    private SystemConfigMasterManager systemConfigMasterManager;

    @Override
    public ServerResponse createSystemConfig(SystemConfigRequest systemConfigRequest) {
        SystemConfigMaster systemConfigMasterEntity = systemConfigMasterManager.findById(systemConfigRequest.getSystemConfigMasterId());

        systemConfigManager.checkItAlreadyExists(null, systemConfigRequest);

        SystemConfig systemConfig = SystemConfigMapper.convertToCreateSystemConfig(systemConfigRequest, systemConfigMasterEntity);

        systemConfigManager.save(systemConfig);
        return systemConfigResponse(systemConfig);
    }

    @Override
    public ServerResponse getSystemConfig() {
        List<SystemConfigDTO> systemConfigDTOS = systemConfigRepository.findAll()
                .stream()
                .map(systemConfig -> SystemConfigMapper.convertToSystemConfigDTO(systemConfig))
                .collect(Collectors.toList());

        SystemConfigResponse systemConfigResponse = new SystemConfigResponse();
        systemConfigResponse.setSystemConfig(systemConfigDTOS);
        return ResponseMsg.successResponse(MsgConstant.Data.SUCCESS, systemConfigResponse);
    }

    @Override
    public ServerResponse getSystemConfigId(Integer id) {
        SystemConfig systemConfigEntity = systemConfigManager.findById(id);
        return systemConfigResponse(systemConfigEntity);
    }

    @Override
    public ServerResponse modifySystemConfig(Integer id, SystemConfigRequest systemConfigRequest) {
        SystemConfigMaster systemConfigMasterEntity = systemConfigMasterManager.findById(systemConfigRequest.getSystemConfigMasterId());
        SystemConfig systemConfigEntity = systemConfigManager.findById(id);

        systemConfigManager.checkItAlreadyExists(id, systemConfigRequest);

        SystemConfig systemConfig = SystemConfigMapper.convertToModifySystemConfig(systemConfigEntity, systemConfigRequest, systemConfigMasterEntity);
        systemConfigManager.save(systemConfig);

        return systemConfigResponse(systemConfig);
    }

    @Override
    public ServerResponse modifyStatus(Integer id, StatusRequest statusRequest) {
        SystemConfig systemConfigEntity = systemConfigManager.findById(id);
        systemConfigEntity.setActive(statusRequest.getActive());

        systemConfigManager.save(systemConfigEntity);
        return systemConfigResponse(systemConfigEntity);
    }

    private ServerResponse systemConfigResponse(SystemConfig systemConfig) {
        SystemConfigDTO systemConfigDTO = SystemConfigMapper.convertToSystemConfigDTO(systemConfig);
        return ResponseMsg.successResponse(MsgConstant.Data.SUCCESS, systemConfigDTO);
    }
}
