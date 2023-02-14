package com.f1soft.campaign.web.service.bli.impl;

import com.f1soft.campaign.common.constant.MsgConstant;
import com.f1soft.campaign.common.dto.ServerResponse;
import com.f1soft.campaign.common.exception.ResourceAlreadyExistException;
import com.f1soft.campaign.common.util.ResponseMsg;
import com.f1soft.campaign.entities.model.TutionGroup;
import com.f1soft.campaign.web.bli.manager.TeacherManager;
import com.f1soft.campaign.web.campaign.dto.request.bli.TeacherCreateRequest;
import com.f1soft.campaign.web.service.bli.TeacherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Bikash Shah
 */
@Slf4j
@Component
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private TeacherManager teacherManager;

    @Override
    public ServerResponse createTeacher(TeacherCreateRequest teacherCreateRequest) {

        teacherManager.checkDuplicateTeacher(teacherCreateRequest.getFirstName(),teacherCreateRequest.getMobileNumber1());
        if(teacherCreateRequest.getAssignedGroupId() != null) {
            if (teacherManager.checkIfGroupIsAssigned(teacherCreateRequest.getAssignedGroupId())){
                throw new ResourceAlreadyExistException(ResponseMsg.failureResponse(MsgConstant.BLI.TEACHER_ALREADY_ASSIGNED));
            }
        }
        return teacherManager.createTeacher(teacherCreateRequest);
    }
}
