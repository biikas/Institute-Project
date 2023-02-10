package com.f1soft.campaign.transaction.connector.transaction;

import com.f1soft.campaign.common.config.application.SystemConfig;
import com.f1soft.campaign.common.constant.AppConfigConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/*
 * @author Rashim Dhaubanjar
 */
@Slf4j
@Component
public class TransactionRequestFactory {

    @Autowired
    private SystemConfig systemConfig;

    @Autowired
    @Qualifier("live")
    private BankconnectTransactionOperations transactionOperations;

    @Autowired
    @Qualifier("local")
    private BankconnectTransactionOperations localTransactionOperations;

    public BankconnectTransactionOperations getTransactionOperations() {
        if (systemConfig.appConfig(AppConfigConstant.BANCSCONNECT_MODE).equalsIgnoreCase("local")) {
            return localTransactionOperations;
        } else if (systemConfig.appConfig(AppConfigConstant.BANCSCONNECT_MODE).equalsIgnoreCase("live")) {
            return transactionOperations;
        }
        throw new IllegalStateException();
    }


}
