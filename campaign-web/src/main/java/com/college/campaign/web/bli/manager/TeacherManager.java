package com.college.campaign.web.bli.manager;

import com.college.campaign.common.constant.MsgConstant;
import com.college.campaign.common.dto.ServerResponse;
import com.college.campaign.common.exception.ResourceAlreadyExistException;
import com.college.campaign.common.util.MessageComposer;
import com.college.campaign.common.util.ResponseMsg;
import com.college.campaign.entities.model.Teacher;
import com.college.campaign.repository.TeacherRepository;
import com.college.campaign.repository.TutionGroupRepository;
import com.college.campaign.web.bli.mapper.TeacherMapper;
import com.college.campaign.web.bli.dto.TeacherCreateRequest;
import com.college.campaign.web.constant.MsgParameter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 * @author Bikash Shah
 */
@Slf4j
@Component
public class TeacherManager {

    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    private TutionGroupRepository tutionGroupRepository;

    public ServerResponse createTeacher(TeacherCreateRequest teacherCreateRequest) {

        Teacher teacher = TeacherMapper.convertToCreateTeacher(teacherCreateRequest);
        teacherRepository.save(teacher);
        return ResponseMsg.successResponse(MsgConstant.BLI.TEACHER_REGISTERED_SUCCESS, MessageComposer.getParameters(MsgParameter.TEACHER, teacher.getFirstName()));
    }

    public boolean checkDuplicateTeacher(String firstName, String mobileNumber) {
        List<Teacher> teachers = teacherRepository.findTeacherByFirstNameAndMobileNumber1(firstName, mobileNumber);
        if (teachers.isEmpty()) {
            return true;
        } else {
            throw new ResourceAlreadyExistException(ResponseMsg.failureResponse(MsgConstant.BLI.TEACHER_ALREADY_EXIST));
        }
    }

    public Boolean checkIfGroupIsAssigned(String groupId) {
        Optional<TutionGroup> tutionGroup = tutionGroupRepository.findTutionGroupById(Long.parseLong(groupId));
        if (tutionGroup.isPresent()) {
            return true;
        } else {
            return false;
        }
    }
}
