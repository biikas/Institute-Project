package com.f1soft.campaign.web.controller.campaign;

import com.f1soft.campaign.common.dto.ServerResponse;
import com.f1soft.campaign.common.log.SkipAPILogging;
import com.f1soft.campaign.common.util.ResponseBuilder;
import com.f1soft.campaign.web.service.EventTypeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Bikash Shah
 */
@Slf4j
@RestController
@RequestMapping("event-type")
public class EventTypeController {

    @Autowired
    private EventTypeService eventTypeService;

    @SkipAPILogging
    @GetMapping( produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> eventTypes() {
        ServerResponse serverResponse = eventTypeService.getEventTypes();
        return ResponseBuilder.response(serverResponse);
    }

    @SkipAPILogging
    @GetMapping(value = "custom", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> eventTypeCustomView() {
        ServerResponse serverResponse = eventTypeService.eventTypeCustomView();
        return ResponseBuilder.response(serverResponse);
    }
}
