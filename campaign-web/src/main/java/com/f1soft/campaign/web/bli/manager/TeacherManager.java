package com.f1soft.campaign.web.bli.manager;

import com.f1soft.campaign.common.constant.MsgConstant;
import com.f1soft.campaign.common.exception.ResourceAlreadyExistException;
import com.f1soft.campaign.common.util.ResponseMsg;
import com.f1soft.campaign.entities.model.Teacher;
import com.f1soft.campaign.repository.TeacherRepository;
import com.f1soft.campaign.web.campaign.dto.request.bli.TeacherCreateRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.function.ServerResponse;

import java.util.List;

/**
 * @author Bikash Shah
 */
@Slf4j
@Component
public class TeacherManager {

    @Autowired
    private TeacherRepository teacherRepository;

    public ServerResponse createTeacher(TeacherCreateRequest teacherCreateRequest){

        return null;

    }

    public boolean checkDuplicateTeacher(String firstName, String mobileNumber){
        List<Teacher> teachers = teacherRepository.findTeacherByFirstNameAndMobileNumber1(firstName,mobileNumber);
        if(teachers.isEmpty()){
            return true;
        }else {
            throw new ResourceAlreadyExistException(ResponseMsg.failureResponse(MsgConstant.BLI.TEACHER_ALREADY_EXIST));
        }
    }
}
