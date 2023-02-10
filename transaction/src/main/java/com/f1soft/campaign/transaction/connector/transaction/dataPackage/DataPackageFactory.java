package com.f1soft.campaign.transaction.connector.transaction.dataPackage;

import com.f1soft.campaign.common.config.application.SystemConfig;
import com.f1soft.campaign.common.constant.AppConfigConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * @author Bikash Shah
 */
@Slf4j
@Component
public class DataPackageFactory {

    @Autowired
    private SystemConfig systemConfig;

    @Autowired
    @Qualifier("live")
    private DataPackageOperations liveDataPackageOperations;

    @Autowired
    @Qualifier("local")
    private DataPackageOperations localDataPackageOperations;

    public DataPackageOperations getDataPackageOperationsMode() {
        String mode = systemConfig.appConfig(AppConfigConstant.DATA_PACK_MODE);
        if (mode.equalsIgnoreCase("live")) {
            return liveDataPackageOperations;
        } else if (mode.equalsIgnoreCase("local")) {
            return localDataPackageOperations;
        }
        throw new IllegalStateException();
    }
}
