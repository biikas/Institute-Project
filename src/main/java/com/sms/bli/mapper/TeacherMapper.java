package com.sms.bli.mapper;

import com.sms.bli.entities.Teacher;
import com.sms.bli.request.TeacherRequest;
import com.sms.bli.response.TeacherResponse;

import java.util.Date;

/**
 * @author Bikash Shah
 */
public class TeacherMapper {

    public static final Teacher convertToCreateTeacherProfile(TeacherRequest teacherRequest){
        Teacher teacher = new Teacher();
        teacher.setAddress(teacherRequest.getAddress());
        teacher.setCourseId(teacherRequest.getCourseId());
        teacher.setGender(teacherRequest.getGender());
        teacher.setDateOfBirth(teacherRequest.getDateOfBirth());
        teacher.setName(teacherRequest.getName());
        teacher.setMiddleName(teacherRequest.getMiddleName());
        teacher.setJoinedDate(new Date());
        teacher.setMobileNumber(teacherRequest.getMobileNumber());
        teacher.setLastName(teacherRequest.getLastName());
        teacher.setCourseId("1");
        teacher.setSpecifications(teacherRequest.getSpecifications());
        teacher.setSubjects(teacherRequest.getSubjects());
        return teacher;
    }

    public static final TeacherResponse convertToGetAllTeacherProfile(Teacher teacher){
        TeacherResponse teacherResponse = new TeacherResponse();
        teacherResponse.setAddress(teacher.getAddress());
        teacherResponse.setCourseId(teacher.getCourseId());
        teacherResponse.setGender(teacher.getGender());
        teacherResponse.setDateOfBirth(teacher.getDateOfBirth());
        teacherResponse.setName(teacher.getName());
        teacherResponse.setMiddleName(teacher.getMiddleName());
        teacherResponse.setJoinedDate(new Date());
        teacherResponse.setMobileNumber(teacher.getMobileNumber());
        teacherResponse.setLastName(teacher.getLastName());
        teacherResponse.setCourseId("1");
        teacherResponse.setSpecifications(teacher.getSpecifications());
        return teacherResponse;
    }
}
