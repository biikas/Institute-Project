package com.f1soft.campaign.web.service.bli.impl;

import com.f1soft.campaign.common.dto.ServerResponse;
import com.f1soft.campaign.web.bli.manager.TeacherManager;
import com.f1soft.campaign.web.campaign.dto.request.bli.TeacherCreateRequest;
import com.f1soft.campaign.web.service.bli.TeacherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Bikash Shah
 */
@Slf4j
@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private TeacherManager teacherManager;

    @Override
    public ServerResponse createTeacher(TeacherCreateRequest teacherCreateRequest) {

        teacherManager.checkDuplicateTeacher(teacherCreateRequest.getFirstName(),teacherCreateRequest.getMobileNumber1());
        //return campaignManager.createCampaign(createCampaignRequest, LoginProvider.getApplicationUser());
        return null;
    }
}
