package com.college.campaign.web.service.bli;

import com.college.campaign.common.dto.ServerResponse;
import com.college.campaign.web.bli.dto.TeacherCreateRequest;
import com.college.campaign.web.bli.dto.TeacherSearchRequest;

public interface TeacherService {

    ServerResponse createTeacher(TeacherCreateRequest teacherCreateRequest);

    ServerResponse searchCampaign(TeacherSearchRequest teacherSearchRequest);
}
