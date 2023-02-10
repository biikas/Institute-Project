package com.f1soft.campaign.web.service.impl;

import com.f1soft.campaign.common.constant.MsgConstant;
import com.f1soft.campaign.common.dto.PageResponse;
import com.f1soft.campaign.common.dto.ServerResponse;
import com.f1soft.campaign.common.util.ResponseMsg;
import com.f1soft.campaign.entities.model.CustomCBSConnection;
import com.f1soft.campaign.entities.model.CustomCBSQuery;
import com.f1soft.campaign.repository.CustomCbsConnectionRepository;
import com.f1soft.campaign.repository.CustomCbsQueryRepository;
import com.f1soft.campaign.repository.Util.SearchQueryParameter;
import com.f1soft.campaign.web.dto.CustomCbsQueryDTO;
import com.f1soft.campaign.web.dto.request.CustomCbsQueryRequest;
import com.f1soft.campaign.web.dto.request.StatusRequest;
import com.f1soft.campaign.web.manager.CustomCbsQueryManager;
import com.f1soft.campaign.web.mapper.CustomCbsQueryMapper;
import com.f1soft.campaign.web.service.CustomCbsQueryService;
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
public class CustomCbsQueryServiceImpl implements CustomCbsQueryService {

    @Autowired
    private CustomCbsQueryManager customCbsQueryManager;
    @Autowired
    private CustomCbsQueryRepository customCbsQueryRepository;
    @Autowired
    private CustomCbsConnectionRepository customCbsConnectionRepository;

    @Override
    public ServerResponse getAllCustomCBSQueryList(SearchQueryParameter searchQueryParameter) {
        Page<CustomCBSQuery> cbsQueries = customCbsQueryRepository
                .findAll(customCbsQueryRepository.searchCBSQuery(searchQueryParameter.getSearch()),
                        PageRequest.of(searchQueryParameter.getPage(), searchQueryParameter.getSize(), Sort.Direction.DESC, "id"));

        List<CustomCbsQueryDTO> cbsQueryDTOS = cbsQueries.getContent().stream()
                .map(CustomCbsQueryMapper::convertToCustomCbsQueryDTO)
                .collect(Collectors.toList());

        PageResponse pageResponse = new PageResponse(cbsQueryDTOS, cbsQueries.getTotalElements());
        return ResponseMsg.successResponse(MsgConstant.Data.SUCCESS, pageResponse);
    }

    @Override
    public ServerResponse execute(Long id) {
        CustomCBSQuery cbsQuery = customCbsQueryManager.findById(id);
        CustomCBSConnection cbsConnection = customCbsConnectionRepository.getCBSConnectionById(cbsQuery.getCustomCbsConnection().getId());
        return customCbsQueryManager.databaseConnection(cbsConnection, cbsQuery);
    }

    @Override
    public ServerResponse getById(Long id) {
        CustomCBSQuery customCBSQuery = customCbsQueryManager.findById(id);
        return cbsQueryResponse(customCBSQuery);
    }

    @Override
    public ServerResponse create(CustomCbsQueryRequest cbsQueryRequest) {
        customCbsQueryManager.checkIfAlreadyExist(cbsQueryRequest);
        CustomCBSConnection customCBSConnection = customCbsConnectionRepository.getCBSConnectionById(cbsQueryRequest.getCbsConnectionId());
        CustomCBSQuery customCBSQuery = CustomCbsQueryMapper.convertToCreateCustomCBSQuery(cbsQueryRequest, customCBSConnection);
        customCbsQueryManager.save(customCBSQuery);
        return ResponseMsg.successResponse(MsgConstant.Data.CUSTOM_QUERY_CREATE_SUCCESS);
    }

    @Override
    public ServerResponse modify(Long id, CustomCbsQueryRequest cbsQueryRequest) {
        CustomCBSQuery customCbsQueryEntity = customCbsQueryManager.findById(id);
        if (!customCbsQueryEntity.getQueryCode().equalsIgnoreCase(cbsQueryRequest.getQueryCode())) {
            customCbsQueryManager.checkIfAlreadyExist(cbsQueryRequest);
        }
        CustomCBSConnection customCBSConnection = customCbsConnectionRepository.getCBSConnectionById(cbsQueryRequest.getCbsConnectionId());
        CustomCBSQuery customCBSQuery = CustomCbsQueryMapper.convertToModifyCustomCBSQuery(customCbsQueryEntity, cbsQueryRequest, customCBSConnection);
        customCbsQueryManager.save(customCBSQuery);
        return ResponseMsg.successResponse(MsgConstant.Data.CUSTOM_QUERY_MODIFY_SUCCESS);
    }

    @Override
    public ServerResponse modifyStatus(Long id, StatusRequest statusRequest) {
        CustomCBSQuery customCbsQueryEntity = customCbsQueryManager.findById(id);
        customCbsQueryEntity.setActive(statusRequest.getActive());
        customCbsQueryManager.save(customCbsQueryEntity);

        CustomCbsQueryDTO customCbsQueryDTO = CustomCbsQueryMapper.convertToCustomCbsQueryDTO(customCbsQueryEntity);
        return ResponseMsg.successResponse(MsgConstant.Data.STATUS_CHANGE_SUCCESS, customCbsQueryDTO);
    }

    private ServerResponse cbsQueryResponse(CustomCBSQuery customCBSQuery) {
        CustomCbsQueryDTO customCbsQueryDTO = CustomCbsQueryMapper.convertToCustomCbsQueryDTO(customCBSQuery);
        return ResponseMsg.successResponse(MsgConstant.Data.SUCCESS, customCbsQueryDTO);
    }
}
