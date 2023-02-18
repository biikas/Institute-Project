package com.college.campaign.web.controller.bli;

import com.college.campaign.common.dto.ServerResponse;
import com.college.campaign.common.log.SkipAPILogging;
import com.college.campaign.common.util.ResponseBuilder;
import com.college.campaign.web.bli.dto.TeacherCreateRequest;
import com.college.campaign.web.bli.dto.TeacherSearchRequest;
import com.college.campaign.web.service.bli.TeacherService;
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
    private TeacherService teacherService;

    @SkipAPILogging
    @PostMapping(value = "search", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getCampaignList(@NotNull @Valid @RequestBody TeacherSearchRequest teacherSearchRequest) {
        ServerResponse serverResponse = teacherService.searchCampaign(teacherSearchRequest);
        return ResponseBuilder.response(serverResponse);
    }

    @PostMapping(value = "create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createTeacher(@NotNull @Valid @RequestBody TeacherCreateRequest teacherCreateRequest) {
        ServerResponse serverResponse = teacherService.createTeacher(teacherCreateRequest);
        return ResponseBuilder.response(serverResponse);
    }


}
