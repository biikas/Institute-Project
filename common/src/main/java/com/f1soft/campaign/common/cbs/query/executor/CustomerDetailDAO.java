package com.f1soft.campaign.common.cbs.query.executor;

import com.f1soft.campaign.common.cbs.dto.CustomerDetailDTO;
import com.f1soft.campaign.common.cbs.dto.CustomerProfileDTO;

import java.util.List;

/**
 * @author Shreetika Panta
 */
public interface CustomerDetailDAO {

    List<CustomerDetailDTO> customerDetail(String query);
}
