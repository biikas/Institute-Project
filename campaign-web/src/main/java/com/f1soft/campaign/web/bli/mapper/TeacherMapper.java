package com.f1soft.campaign.web.bli.mapper;

import com.f1soft.campaign.entities.model.Teacher;
import com.f1soft.campaign.web.bli.dto.TeacherCreateRequest;
import com.f1soft.campaign.web.bli.dto.TeacherResponse;
import com.f1soft.campaign.web.config.provider.LoginProvider;

import java.util.Date;

/**
 * @author Bikash Shah
 */
public class TeacherMapper {

    public static Teacher convertToCreateTeacher(TeacherCreateRequest teacherCreateRequest) {
        Teacher teacher = new Teacher();
        teacher.setCreatedBy(LoginProvider.getApplicationUser());
        if (teacherCreateRequest.getMonthlySalary() != null) {
            teacher.setAmountToPay(String.valueOf(teacherCreateRequest.getMonthlySalary()));
        }
        if (teacherCreateRequest.getCommissionValue() != null) {
            teacher.setCommissionValue(teacherCreateRequest.getCommissionValue());
        }
        teacher.setFirstName(teacherCreateRequest.getFirstName());
        if (teacherCreateRequest.getMiddleName() != null) {
            teacher.setMiddleName(teacherCreateRequest.getMiddleName());
        }
        teacher.setLastName(teacherCreateRequest.getLastName());
        teacher.setEmail(teacherCreateRequest.getEmail());
        teacher.setMobileNumber1(teacherCreateRequest.getMobileNumber1());
        teacher.setMobileNumber2(teacherCreateRequest.getMobileNumber2());
        teacher.setPermanentAddress(teacherCreateRequest.getPermanentAddress());
        teacher.setTemporaryAddress(teacherCreateRequest.getTemporaryAddress());
        teacher.setCreatedDate(new Date());
        teacher.setSpecializedSubejcts(teacherCreateRequest.getSpecializedSubjects());
        teacher.setJobDescription(teacherCreateRequest.getJobDescription());
        teacher.setQualification(teacherCreateRequest.getQualification());

        return teacher;
    }

    public static TeacherResponse convertToTeacherResponse(Teacher teacher) {
        TeacherResponse teacherResponse = new TeacherResponse();
        if (teacher.getAmountToPay() != null) {
            teacherResponse.setMonthlySalary(Double.valueOf(String.valueOf(teacher.getAmountToPay())));
        }
        if (teacher.getCommissionValue() != null) {
            teacherResponse.setCommissionValue(teacher.getCommissionValue());
        }
        teacherResponse.setFirstName(teacher.getFirstName());
        if (teacher.getMiddleName() != null) {
            teacherResponse.setMiddleName(teacher.getMiddleName());
        }
        teacherResponse.setLastName(teacher.getLastName());
        teacherResponse.setEmail(teacher.getEmail());
        teacherResponse.setMobileNumber1(teacher.getMobileNumber1());
        teacherResponse.setMobileNumber2(teacher.getMobileNumber2());
        teacherResponse.setPermanentAddress(teacher.getPermanentAddress());
        teacherResponse.setTemporaryAddress(teacher.getTemporaryAddress());
        teacherResponse.setSpecializedSubjects(teacher.getSpecializedSubejcts());
        teacherResponse.setJobDescription(teacher.getJobDescription());
        teacherResponse.setQualification(teacher.getQualification());

        return teacherResponse;
    }
}
