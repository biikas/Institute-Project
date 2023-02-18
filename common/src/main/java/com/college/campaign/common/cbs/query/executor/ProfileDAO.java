package com.college.campaign.common.cbs.query.executor;

import com.college.campaign.common.cbs.dto.CustomerProfileDTO;

import java.util.List;

/**
 * @author Prajwol Hada
 */
public interface ProfileDAO {

    List<CustomerProfileDTO> customerProfile(String query);
}
