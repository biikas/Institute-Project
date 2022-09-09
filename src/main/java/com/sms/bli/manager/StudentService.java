package com.sms.bli.manager;

import com.sms.bli.dto.ServerResponse;
import com.sms.bli.request.StudentRequest;

public interface StudentService {

     ServerResponse createStudent(StudentRequest studentRequest);

     ServerResponse getAllStudent();

     ServerResponse changeStatus(Long id);
}
