package com.f1soft.campaign.web.service.campaign.impl;

import com.f1soft.campaign.common.constant.MsgConstant;
import com.f1soft.campaign.common.dto.ServerResponse;
import com.f1soft.campaign.common.util.ResponseMsg;
import com.f1soft.campaign.entities.model.CustomCBSQuery;
import com.f1soft.campaign.entities.model.EventType;
import com.f1soft.campaign.repository.CustomCbsQueryRepository;
import com.f1soft.campaign.repository.EventTypeRepository;
import com.f1soft.campaign.web.dto.EventTypeListResponse;
import com.f1soft.campaign.web.dto.response.CustomCbsQueryResponse;
import com.f1soft.campaign.web.mapper.EventTypeMapper;
import com.f1soft.campaign.web.service.campaign.EventTypeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Bikash Shah
 */
@Slf4j
@Service
public class EventTypeServiceImpl implements EventTypeService {

    @Autowired
    private EventTypeRepository eventTypeRepository;
    @Autowired
    private CustomCbsQueryRepository customCbsQueryRepository;

    @Override
    public ServerResponse getEventTypes() {
        List<EventType> eventTypes = eventTypeRepository.getAllEventType();

        EventTypeListResponse eventTypeListResponse = EventTypeMapper.convertToEventTypeListResponse(eventTypes);

        return ResponseMsg.successResponse(MsgConstant.Data.SUCCESS, eventTypeListResponse);
    }

    @Override
    public ServerResponse eventTypeCustomView() {
        List<CustomCBSQuery> customCBSQueries = customCbsQueryRepository.findAll();

        CustomCbsQueryResponse customCbsQueryResponse = EventTypeMapper.convertToCustomCbsQueryList(customCBSQueries);

        return ResponseMsg.successResponse(MsgConstant.Data.SUCCESS, customCbsQueryResponse);
    }
}
