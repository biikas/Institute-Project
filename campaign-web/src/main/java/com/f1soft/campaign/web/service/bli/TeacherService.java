package com.f1soft.campaign.web.service.bli;

import com.f1soft.campaign.common.dto.ServerResponse;
import com.f1soft.campaign.web.campaign.dto.request.bli.TeacherCreateRequest;

public interface TeacherService {
    ServerResponse createTeacher(TeacherCreateRequest teacherCreateRequest);
}
