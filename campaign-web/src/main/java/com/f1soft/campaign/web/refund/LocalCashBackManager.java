package com.f1soft.campaign.web.refund;


import com.f1soft.campaign.common.dto.ServerResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;


@Slf4j
@Component
@Qualifier("local")
public class LocalCashBackManager implements RedeemOperations {

    @Override
    public ServerResponse redeem(List<Long> offerTransactionIdList) {
        ServerResponse serverResponse = new ServerResponse();
        serverResponse.setMessage("Cash Back successful");
        serverResponse.setSuccess(true);
        return serverResponse;
    }
}