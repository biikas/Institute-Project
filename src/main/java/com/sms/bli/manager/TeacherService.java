package com.sms.bli.manager;

import com.sms.bli.dto.ServerResponse;
import com.sms.bli.request.TeacherRequest;

public interface TeacherService {

    ServerResponse createTeacherProfile(TeacherRequest teacherRequest);

    ServerResponse getTeachersProfile();
}
