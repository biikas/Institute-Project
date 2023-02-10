package com.f1soft.campaign.web.crud.systemconfig;

import com.f1soft.campaign.common.constant.MsgConstant;
import com.f1soft.campaign.common.exception.DataNotFoundException;
import com.f1soft.campaign.common.exception.ResourceAlreadyExistException;
import com.f1soft.campaign.common.util.MessageComposer;
import com.f1soft.campaign.common.util.ResponseMsg;
import com.f1soft.campaign.entities.model.SystemConfig;
import com.f1soft.campaign.repository.SystemConfigRepository;
import com.f1soft.campaign.web.constant.MsgParameter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
public class SystemConfigManager {

    @Autowired
    private SystemConfigRepository systemConfigRepository;

    public SystemConfig findById(Integer id) {
        return systemConfigRepository.findSystemConfigById(id)
                .orElseThrow(() -> new DataNotFoundException(ResponseMsg.failureResponse(MsgConstant.Data.NOT_FOUND)));
    }

    public boolean checkItAlreadyExists(Integer id, SystemConfigRequest systemConfigRequest) {
        Optional<SystemConfig> optSystemConfig = systemConfigRepository.findSystemConfigByParamCode(systemConfigRequest.getParamCode());
        if (optSystemConfig.isPresent()) {
            if (id != null && optSystemConfig.get().getId() != id) {
                throw new ResourceAlreadyExistException(ResponseMsg.failureResponse(
                        MsgConstant.DynamicData.DUPLICATE,
                        MessageComposer.getParameters(MsgParameter.DUPLICATE_DATA, "SystemConfig")));
            }

        }
        return false;
    }

    public boolean save(SystemConfig systemConfig) {
        systemConfigRepository.save(systemConfig);
        return true;
    }
}
