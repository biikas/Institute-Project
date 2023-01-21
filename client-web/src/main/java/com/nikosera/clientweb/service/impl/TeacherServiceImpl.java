package com.nikosera.clientweb.service.impl;


import com.nikosera.clientweb.dto.ServerResponse;
import com.nikosera.clientweb.dto.request.TeacherRequest;
import com.nikosera.clientweb.dto.response.TeacherResponse;
import com.nikosera.clientweb.mapper.TeacherMapper;
import com.nikosera.clientweb.repository.TeacherRepository;
import com.nikosera.clientweb.service.TeacherService;
import com.nikosera.entity.Teacher;
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
