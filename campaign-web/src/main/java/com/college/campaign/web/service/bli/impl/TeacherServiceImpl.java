package com.college.campaign.web.service.bli.impl;

import com.college.campaign.web.bli.dto.TeacherCreateRequest;
import com.college.campaign.web.bli.dto.TeacherListResponse;
import com.college.campaign.web.bli.dto.TeacherResponse;
import com.college.campaign.web.bli.dto.TeacherSearchRequest;
import com.college.campaign.web.bli.manager.TeacherManager;
import com.college.campaign.web.bli.mapper.TeacherMapper;
import com.college.campaign.web.service.bli.TeacherService;
import com.college.campaign.common.constant.MsgConstant;
import com.college.campaign.common.dto.ServerResponse;
import com.college.campaign.common.exception.ResourceAlreadyExistException;
import com.college.campaign.common.util.ResponseMsg;
import com.college.campaign.entities.model.Teacher;
import com.college.campaign.repository.TeacherRepository;
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
