package com.f1soft.campaign.web.service.bli.impl;

import com.f1soft.campaign.common.constant.MsgConstant;
import com.f1soft.campaign.common.dto.ServerResponse;
import com.f1soft.campaign.common.exception.ResourceAlreadyExistException;
import com.f1soft.campaign.common.util.ResponseMsg;
import com.f1soft.campaign.entities.model.Teacher;
import com.f1soft.campaign.repository.TeacherRepository;
import com.f1soft.campaign.web.bli.dto.TeacherCreateRequest;
import com.f1soft.campaign.web.bli.dto.TeacherListResponse;
import com.f1soft.campaign.web.bli.dto.TeacherResponse;
import com.f1soft.campaign.web.bli.dto.TeacherSearchRequest;
import com.f1soft.campaign.web.bli.manager.TeacherManager;
import com.f1soft.campaign.web.bli.mapper.TeacherMapper;
import com.f1soft.campaign.web.service.bli.TeacherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Bikash Shah
 */
@Slf4j
@Component
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private TeacherManager teacherManager;
    @Autowired
    private TeacherRepository teacherRepository;

    @Override
    public ServerResponse createTeacher(TeacherCreateRequest teacherCreateRequest) {

        teacherManager.checkDuplicateTeacher(teacherCreateRequest.getFirstName(), teacherCreateRequest.getMobileNumber1());
        if (teacherCreateRequest.getAssignedGroupId() != null) {
            if (teacherManager.checkIfGroupIsAssigned(teacherCreateRequest.getAssignedGroupId())) {
                throw new ResourceAlreadyExistException(ResponseMsg.failureResponse(MsgConstant.BLI.TEACHER_ALREADY_ASSIGNED));
            }
        }
        return teacherManager.createTeacher(teacherCreateRequest);
    }

    @Override
    public ServerResponse searchCampaign(TeacherSearchRequest teacherSearchRequest) {

        List<Teacher> teachers = teacherRepository.searchQuery(teacherSearchRequest.getName(), teacherSearchRequest.getMobileNumber());

        List<TeacherResponse> teacherResponses = teachers.stream()
                .map(teacher -> TeacherMapper.convertToTeacherResponse(teacher)

        ).collect(Collectors.toList());

        TeacherListResponse teacherListResponse = new TeacherListResponse();
        teacherListResponse.setTeacherList(teacherResponses);
        return ResponseMsg.successResponse(MsgConstant.Data.SUCCESS, teacherListResponse);
    }
}
