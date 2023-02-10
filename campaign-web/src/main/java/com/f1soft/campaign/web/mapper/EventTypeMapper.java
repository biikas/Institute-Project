package com.f1soft.campaign.web.mapper;

import com.f1soft.campaign.entities.model.CustomCBSQuery;
import com.f1soft.campaign.entities.model.EventType;
import com.f1soft.campaign.web.dto.CustomCbsQueryDTO;
import com.f1soft.campaign.web.dto.EventTypeListResponse;
import com.f1soft.campaign.web.dto.EventTypeResponse;
import com.f1soft.campaign.web.dto.response.CustomCbsQueryResponse;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Bikash Shah
 */
public class EventTypeMapper {

    private EventTypeMapper() {

    }

    public static EventTypeListResponse convertToEventTypeListResponse(List<EventType> eventTypes) {

        EventTypeListResponse eventTypeListResponse = new EventTypeListResponse();
        List<EventTypeResponse> eventTypeResponses = eventTypes
                .stream()
                .map(EventTypeMapper::convertToEventTypeResponse)
                .collect(Collectors.toList());
        eventTypeListResponse.setEventLists(eventTypeResponses);
        return eventTypeListResponse;
    }

    public static EventTypeResponse convertToEventTypeResponse(EventType eventType) {
        EventTypeResponse eventTypeResponse = new EventTypeResponse();
        eventTypeResponse.setId(eventType.getId());
        eventTypeResponse.setCode(eventType.getCode());
        eventTypeResponse.setName(eventType.getName());
        eventTypeResponse.setType(eventType.getType());
        return eventTypeResponse;
    }

    public static CustomCbsQueryResponse convertToCustomCbsQueryList(List<CustomCBSQuery> customCBSQueries) {
        CustomCbsQueryResponse customCbsQueryResponse = new CustomCbsQueryResponse();
        List<CustomCbsQueryDTO> customCbsQueryDTOS = customCBSQueries
                .stream()
                .map(EventTypeMapper::convertToCustomCbsQueryResponse)
                .collect(Collectors.toList());

        customCbsQueryResponse.setCustomCbsQueries(customCbsQueryDTOS);
        return customCbsQueryResponse;
    }

    private static CustomCbsQueryDTO convertToCustomCbsQueryResponse(CustomCBSQuery customCBSQuery) {
        CustomCbsQueryDTO customCbsQueryDTO = new CustomCbsQueryDTO();
        customCbsQueryDTO.setId(customCBSQuery.getId());
        customCbsQueryDTO.setQuery(customCBSQuery.getSqlQuery());
        customCbsQueryDTO.setQueryCode(customCBSQuery.getQueryCode());
        customCbsQueryDTO.setQueryName(customCBSQuery.getQueryDescription());
        return customCbsQueryDTO;
    }
}
