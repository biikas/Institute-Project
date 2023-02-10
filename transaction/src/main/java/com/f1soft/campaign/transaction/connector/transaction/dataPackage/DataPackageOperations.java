package com.f1soft.campaign.transaction.connector.transaction.dataPackage;

import com.f1soft.campaign.common.dto.RequestWrapper;
import com.f1soft.campaign.common.dto.ServerResponse;

public interface DataPackageOperations {

    ServerResponse dataPackagePayment(RequestWrapper requestWrapper);
}
