package com.f1soft.campaign.web.controller.bli;

import com.f1soft.campaign.web.campaign.dto.request.bli.TeacherCreateRequest;
import com.f1soft.campaign.web.service.campaign.CampaignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * @author Bikash Shah
 */
@Slf4j
@RestController
@RequestMapping("teacher")
public class TeacherController {
    @Autowired
    private CampaignService campaignService;

//    @SkipAPILogging
//    @PostMapping(value = "search", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<?> getCampaignList(@NotNull @Valid @RequestBody SearchQueryParameter searchQueryParameter) {
//        ServerResponse serverResponse = campaignService.searchCampaign(searchQueryParameter);
//        return ResponseBuilder.response(serverResponse);
//    }

    @PostMapping(value = "create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createTeacher(@NotNull @Valid @RequestBody TeacherCreateRequest teacherCreateRequest) {
//        ServerResponse serverResponse = campaignCrudService.createCampaign(teacherCreateRequest);
//        return ResponseBuilder.response(serverResponse);
        return null;
    }


}
