package com.nikosera.clientweb.service;

import com.nikosera.clientweb.dto.ServerResponse;
import com.nikosera.clientweb.dto.request.TeacherRequest;


public interface TeacherService {

    ServerResponse createTeacherProfile(TeacherRequest teacherRequest);

    ServerResponse getTeachersProfile();
}
