package com.nikosera.clientweb.service.impl;

import com.nikosera.clientweb.dto.ServerResponse;
import com.nikosera.clientweb.dto.request.StudentRequest;
import com.nikosera.clientweb.dto.response.StudentResponse;
import com.nikosera.clientweb.mapper.StudentMapper;
import com.nikosera.clientweb.repository.StudentRepository;
import com.nikosera.clientweb.service.StudentService;
import com.nikosera.entity.Student;
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
