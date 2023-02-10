package com.f1soft.campaign.transaction.connector.transaction.dataPackage;

import com.f1soft.campaign.common.dto.RequestWrapper;
import com.f1soft.campaign.common.dto.ServerResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Bikash Shah
 */
@Slf4j
@Component
public class DataPackageRequestContext {

    @Autowired
    private DataPackageFactory dataPackageFactory;

    public ServerResponse dataPackagePayment(RequestWrapper requestWrapper){
        return dataPackageFactory.getDataPackageOperationsMode().dataPackagePayment(requestWrapper);
    }
}
