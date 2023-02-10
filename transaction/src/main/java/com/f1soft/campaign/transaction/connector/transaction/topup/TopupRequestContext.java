package com.f1soft.campaign.transaction.connector.transaction.topup;

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
public class TopupRequestContext {

    @Autowired
    private TopUpFactory topUpFactory;

    public ServerResponse topUp(RequestWrapper requestWrapper){
        return topUpFactory.getTopupOperationsMode().topupPayment(requestWrapper);
    }

}
