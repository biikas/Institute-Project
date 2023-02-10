package com.f1soft.campaign.web.refund;


import com.f1soft.campaign.common.dto.ServerResponse;

import java.util.List;

public interface RedeemOperations {

    ServerResponse redeem(List<Long> offerTransactionIdList);
}
