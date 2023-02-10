package com.f1soft.campaign.common.cbs.query.executor;

import com.f1soft.campaign.common.cbs.dto.CustomerProfileDTO;

import java.util.List;
import java.util.Map;

/**
 * @author Prajwol Hada
 */
public interface ProfileDAO {

    List<CustomerProfileDTO> customerProfile(String query);
}
