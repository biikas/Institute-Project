package com.f1soft.campaign.web.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author Bikash Shah
 */
@Getter
@Setter
public class EventTypeListResponse {

    private List<EventTypeResponse> eventLists;
}
