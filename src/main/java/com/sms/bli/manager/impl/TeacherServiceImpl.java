package com.sms.bli.manager.impl;

import com.sms.bli.dto.ServerResponse;
import com.sms.bli.entities.Teacher;
import com.sms.bli.manager.TeacherService;
import com.sms.bli.mapper.StudentMapper;
import com.sms.bli.mapper.TeacherMapper;
import com.sms.bli.repository.TeacherRepository;
import com.sms.bli.request.TeacherRequest;
import com.sms.bli.response.StudentResponse;
import com.sms.bli.response.TeacherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Bikash Shah
 */
@Component
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private TeacherRepository teacherRepository;

    @Override
    public ServerResponse createTeacherProfile(TeacherRequest teacherRequest) {
        ServerResponse serverResponse = new ServerResponse();
        try {
            Teacher teacher = TeacherMapper.convertToCreateTeacherProfile(teacherRequest);
            teacherRepository.save(teacher);
            serverResponse.setSuccess(true);
            serverResponse.setCode("0");
            serverResponse.setMessage("Teacher profile created successfully");
            serverResponse.setObj(teacher);

            return serverResponse;
        }catch (Exception e){
            serverResponse.setSuccess(false);
            serverResponse.setMessage("oops something went wrong");
            serverResponse.setCode("1");
            return serverResponse;
        }
    }

    @Override
    public ServerResponse getTeachersProfile() {

        ServerResponse serverResponse = new ServerResponse();
        try{
            List<TeacherResponse> studentResponses = teacherRepository.findAll()
                    .stream()
                    .map(teacher -> TeacherMapper.convertToGetAllTeacherProfile(teacher))
                    .collect(Collectors.toList());

            serverResponse.setSuccess(true);
            serverResponse.setMessage("Teacher List obtained successfully");
            serverResponse.setCode("0");
            serverResponse.setObj(studentResponses);
            return serverResponse;
        }catch (Exception e){
            serverResponse.setMessage("oops some thing went wrong");
            serverResponse.setCode("1");
            serverResponse.setSuccess(false);
            return serverResponse;
        }
    }
}
