package com.f1soft.campaign.common.cbs.query.executor;

import com.f1soft.campaign.common.cbs.dto.CustomRedeemDTO;

import java.util.List;

/**
 * @author Shreetika Panta
 */
public interface CustomRedeemDAO {

    List<CustomRedeemDTO> customRedeemDetail(String query);
}
