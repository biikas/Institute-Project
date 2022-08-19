package com.sms.bli.manager.impl;

import com.sms.bli.dto.ServerResponse;
import com.sms.bli.entities.Student;
import com.sms.bli.manager.StudentService;
import com.sms.bli.mapper.StudentMapper;
import com.sms.bli.repository.StudentRepository;
import com.sms.bli.request.StudentRequest;
import com.sms.bli.response.StudentResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Bikash Shah
 */
@Component
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;
    @Override
    public ServerResponse createStudent(StudentRequest studentRequest) {
        ServerResponse serverResponse = new ServerResponse();
        try {
            Student student = StudentMapper.convertToCreateStudent(studentRequest);
            student = studentRepository.save(student);
            serverResponse.setSuccess(true);
            serverResponse.setCode("0");
            serverResponse.setMessage("Student Created successfully");
            serverResponse.setObj(student);
            return serverResponse;
        }catch (Exception e){
            serverResponse.setSuccess(false);
            serverResponse.setCode("1");
            serverResponse.setMessage("oops some thing went wrong");
            return  serverResponse;
        }
    }

    @Override
    public ServerResponse getAllStudent() {
        ServerResponse serverResponse = new ServerResponse();
        try{
            List<StudentResponse> studentResponses = studentRepository.findAll()
                    .stream()
                    .map(student -> StudentMapper.convertToGetStudentList(student))
                    .collect(Collectors.toList());

            serverResponse.setSuccess(true);
            serverResponse.setMessage("Student List obtained successfully");
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
