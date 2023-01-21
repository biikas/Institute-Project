package com.nikosera.clientweb.service;

import com.nikosera.clientweb.dto.ServerResponse;
import com.nikosera.clientweb.dto.request.StudentRequest;

public interface StudentService {

    ServerResponse createStudent(StudentRequest studentRequest);

    ServerResponse getAllStudent();
}
