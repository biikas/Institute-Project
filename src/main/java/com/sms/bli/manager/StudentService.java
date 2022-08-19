package com.sms.bli.manager;

import com.sms.bli.dto.ServerResponse;
import com.sms.bli.request.StudentRequest;

public interface StudentService {

    public ServerResponse createStudent(StudentRequest studentRequest);

    public ServerResponse getAllStudent();
}
