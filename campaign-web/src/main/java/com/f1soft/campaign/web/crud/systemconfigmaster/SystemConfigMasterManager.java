package com.f1soft.campaign.web.crud.systemconfigmaster;

import com.f1soft.campaign.common.constant.MsgConstant;
import com.f1soft.campaign.common.exception.DataNotFoundException;
import com.f1soft.campaign.common.exception.ResourceAlreadyExistException;
import com.f1soft.campaign.common.util.MessageComposer;
import com.f1soft.campaign.common.util.ResponseMsg;
import com.f1soft.campaign.entities.model.SystemConfigMaster;
import com.f1soft.campaign.repository.SystemConfigMasterRepository;
import com.f1soft.campaign.web.constant.MsgParameter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
public class SystemConfigMasterManager {

    @Autowired
    private SystemConfigMasterRepository systemConfigMasterRepository;

    public SystemConfigMaster findById(Integer id) {
        return systemConfigMasterRepository.findSystemConfigMasterById(id)
                .orElseThrow(() -> new DataNotFoundException(ResponseMsg.failureResponse(MsgConstant.Data.NOT_FOUND)));
    }

    public boolean checkItAlreadyExists(Integer id, SystemConfigMasterRequest systemConfigMasterRequest) {
        Optional<SystemConfigMaster> optSystemConfigMaster = systemConfigMasterRepository.findSystemConfigMasterByCode(systemConfigMasterRequest.getCode());
        if (optSystemConfigMaster.isPresent()) {
            if (id != null && optSystemConfigMaster.get().getId() != id) {
                throw new ResourceAlreadyExistException(ResponseMsg.failureResponse(
                        MsgConstant.DynamicData.DUPLICATE,
                        MessageComposer.getParameters(MsgParameter.DUPLICATE_DATA, "SystemConfig Master")));
            }

        }
        return false;
    }

    public boolean save(SystemConfigMaster systemConfigMaster) {
        systemConfigMasterRepository.save(systemConfigMaster);
        return true;
    }
}
