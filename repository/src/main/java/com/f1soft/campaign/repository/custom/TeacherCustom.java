package com.f1soft.campaign.repository.custom;

import com.f1soft.campaign.entities.model.Teacher;


import java.util.List;

/**
 * @author Bikash Shah
 */
public interface TeacherCustom {

    List<Teacher> searchQuery(String firstName,String mobileNumber);

}
