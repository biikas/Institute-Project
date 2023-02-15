package com.f1soft.campaign.web.service.bli;

import com.f1soft.campaign.common.dto.ServerResponse;
import com.f1soft.campaign.repository.Util.SearchQueryParameter;
import com.f1soft.campaign.web.bli.dto.TeacherCreateRequest;
import com.f1soft.campaign.web.bli.dto.TeacherSearchRequest;

public interface TeacherService {

    ServerResponse createTeacher(TeacherCreateRequest teacherCreateRequest);

    ServerResponse searchCampaign(TeacherSearchRequest teacherSearchRequest);
}
