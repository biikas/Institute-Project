package com.college.campaign.common.cbs.query.executor;

import com.college.campaign.common.cbs.dto.CustomerDetailDTO;

import java.util.List;

/**
 * @author Shreetika Panta
 */
public interface CustomerDetailDAO {

    List<CustomerDetailDTO> customerDetail(String query);
}
