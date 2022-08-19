package com.sms.bli.mapper;

import com.sms.bli.entities.Student;
import com.sms.bli.request.StudentRequest;
import com.sms.bli.response.StudentResponse;

import java.util.Date;

/**
 * @author Bikash Shah
 */

public class StudentMapper {

    public static StudentResponse convertToGetStudentList(Student student){
        StudentResponse studentResponse = new StudentResponse();
        studentResponse.setName(student.getName());
        studentResponse.setAddress(student.getAddress());
        studentResponse.setCourseId(student.getCourseId());
        studentResponse.setGender(student.getGender());
        studentResponse.setDateOfBirth(student.getDateOfBirth());
        studentResponse.setJoinedDate(student.getJoinedDate());
        studentResponse.setMobileNumber(student.getMobileNumber());
        studentResponse.setMiddleName(student.getMiddleName());
        studentResponse.setSubjects(student.getSubjects());
        studentResponse.setLastName(student.getLastName());
        return studentResponse;
    }

    public static Student convertToCreateStudent(StudentRequest studentRequest){
        Student student = new Student();
        student.setAddress(studentRequest.getAddress());
        student.setCourseId("1");
        student.setGender(studentRequest.getGender());
        student.setDateOfBirth(studentRequest.getDateOfBirth());
        student.setJoinedDate(new Date());
        student.setMobileNumber(studentRequest.getMobileNumber());
        student.setLastName(studentRequest.getLastName());
        student.setName(studentRequest.getName());
        student.setMiddleName(studentRequest.getMiddleName());
        student.setSubjects("All");
        return student;
    }
}
