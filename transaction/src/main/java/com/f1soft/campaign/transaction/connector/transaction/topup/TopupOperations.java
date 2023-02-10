package com.f1soft.campaign.transaction.connector.transaction.topup;

import com.f1soft.campaign.common.dto.RequestWrapper;
import com.f1soft.campaign.common.dto.ServerResponse;

public interface TopupOperations {

    ServerResponse topupPayment(RequestWrapper requestWrapper);

}
