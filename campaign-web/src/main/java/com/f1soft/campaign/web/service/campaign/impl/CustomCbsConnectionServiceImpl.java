package com.f1soft.campaign.web.service.campaign.impl;

import com.f1soft.campaign.common.constant.MsgConstant;
import com.f1soft.campaign.common.dto.PageResponse;
import com.f1soft.campaign.common.dto.ServerResponse;
import com.f1soft.campaign.common.util.ResponseMsg;
import com.f1soft.campaign.entities.model.CustomCBSConnection;
import com.f1soft.campaign.repository.CustomCbsConnectionRepository;
import com.f1soft.campaign.repository.Util.SearchQueryParameter;
import com.f1soft.campaign.web.dto.CustomCBSConnectionDTO;
import com.f1soft.campaign.web.dto.request.CustomCBSConnectionRequest;
import com.f1soft.campaign.web.dto.request.StatusRequest;
import com.f1soft.campaign.web.dto.request.TestCBSConnectionRequest;
import com.f1soft.campaign.web.dto.response.CustomCBSConnectionResponse;
import com.f1soft.campaign.web.manager.CustomCBSConnectionManager;
import com.f1soft.campaign.web.mapper.CustomCBSConnectionMapper;
import com.f1soft.campaign.web.service.campaign.CustomCbsConnectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Shreetika Panta
 */

@Service
public class CustomCbsConnectionServiceImpl implements CustomCbsConnectionService {

    @Autowired
    private CustomCBSConnectionManager customCBSConnectionManager;
    @Autowired
    private CustomCbsConnectionRepository customCbsConnectionRepository;

    @Override
    public ServerResponse getAllCustomCBSQueryConnection() {
        List<CustomCBSConnection> customCBSConnectionList = customCbsConnectionRepository.findAll();
        List<CustomCBSConnectionDTO> customCBSConnectionDTOS = customCBSConnectionList.stream()
                .filter(f -> f.getActive() == 'Y')
                .map((cbsConnection -> CustomCBSConnectionMapper.convertToCustomCBSConnection(cbsConnection)))
                .collect(Collectors.toList());

        CustomCBSConnectionResponse cbsConnectionResponse = new CustomCBSConnectionResponse();
        cbsConnectionResponse.setCbsConnections(customCBSConnectionDTOS);

        return ResponseMsg.successResponse(MsgConstant.Data.SUCCESS, cbsConnectionResponse);
    }

    @Override
    public ServerResponse getAllCustomCBSConnectionList(SearchQueryParameter searchQueryParameter) {
        Page<CustomCBSConnection> customCBSConnections = customCbsConnectionRepository
                .findAll(customCbsConnectionRepository.searchCBSConnection(searchQueryParameter.getSearch()),
                        PageRequest.of(searchQueryParameter.getPage(), searchQueryParameter.getSize(), Sort.Direction.DESC, "id"));

        List<CustomCBSConnectionDTO> customCBSConnectionDTOS = customCBSConnections.getContent().stream()
                .map(s -> CustomCBSConnectionMapper.convertToCustomCBSConnection(s))
                .collect(Collectors.toList());

        PageResponse pageResponse = new PageResponse(customCBSConnectionDTOS, customCBSConnections.getTotalElements());
        return ResponseMsg.successResponse(MsgConstant.Data.SUCCESS, pageResponse);
    }

    @Override
    public ServerResponse getById(Long id) {
        CustomCBSConnection cbsConnectionEntity = customCbsConnectionRepository.getCBSConnectionById(id);
        return cbsConnectionResponse(cbsConnectionEntity);
    }

    @Override
    public ServerResponse create(CustomCBSConnectionRequest customCBSConnectionRequest) {
        customCBSConnectionManager.checkIfAlreadyExist(customCBSConnectionRequest);
        CustomCBSConnection customCBSConnection = CustomCBSConnectionMapper.convertToCreateCustomCBSConnection(customCBSConnectionRequest);

        customCBSConnectionManager.save(customCBSConnection);
        return ResponseMsg.successResponse(MsgConstant.Data.CUSTOM_CONNECTION_CREATE_SUCCESS);
    }

    @Override
    public ServerResponse modify(Long id, CustomCBSConnectionRequest customCBSConnectionRequest) {
        CustomCBSConnection customCbsConnectionEntity = customCBSConnectionManager.findById(id);
        if (!customCbsConnectionEntity.getCbsConnectionURL().equalsIgnoreCase(customCBSConnectionRequest.getCbsConnectionURL())) {
            customCBSConnectionManager.checkIfAlreadyExist(customCBSConnectionRequest);
        }

        CustomCBSConnection customCBSConnection = CustomCBSConnectionMapper.convertToModifyCustomCBSConnection(customCbsConnectionEntity, customCBSConnectionRequest);
        customCBSConnectionManager.save(customCBSConnection);
        return ResponseMsg.successResponse(MsgConstant.Data.CUSTOM_CONNECTION_MODIFY_SUCCESS);
    }

    @Override
    public ServerResponse execute(Long id) {
        CustomCBSConnection customCBSConnection = customCbsConnectionRepository.getCBSConnectionById(id);
        TestCBSConnectionRequest testCBSConnectionRequest = CustomCBSConnectionMapper.convertToTestCBSConnection(customCBSConnection);
        return customCBSConnectionManager.databaseConnection(testCBSConnectionRequest);
    }

    @Override
    public ServerResponse modifyStatus(Long id, StatusRequest statusRequest) {
        CustomCBSConnection customCBSConnection = customCbsConnectionRepository.getCBSConnectionById(id);
        customCBSConnection.setActive(statusRequest.getActive());
        customCBSConnectionManager.save(customCBSConnection);

        CustomCBSConnectionDTO customCBSConnectionDTO = CustomCBSConnectionMapper.convertToCustomCBSConnection(customCBSConnection);
        return ResponseMsg.successResponse(MsgConstant.Data.STATUS_CHANGE_SUCCESS, customCBSConnectionDTO);
    }

    public ServerResponse cbsConnectionResponse(CustomCBSConnection customCBSConnection) {
        CustomCBSConnectionDTO customCBSConnectionDTO = CustomCBSConnectionMapper.convertToCustomCBSConnection(customCBSConnection);
        return ResponseMsg.successResponse(MsgConstant.Data.SUCCESS, customCBSConnectionDTO);

    }
}
