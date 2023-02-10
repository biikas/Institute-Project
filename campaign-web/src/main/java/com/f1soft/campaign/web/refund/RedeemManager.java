package com.f1soft.campaign.web.refund;


import com.f1soft.campaign.common.dto.ServerResponse;
import com.f1soft.campaign.web.dto.request.RedeemRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@Qualifier("live")
public class RedeemManager implements RedeemOperations {

    private static final String REFUND = "/refund";

    @Autowired
    private RedeemConnector campaignConnector;

    @Override
    public ServerResponse redeem(List<Long> offerTransactionIdList) {
        return campaignConnector.request(
                REFUND,
                new RedeemRequest(offerTransactionIdList, ""),
                new ParameterizedTypeReference<ServerResponse>() {
                });
    }
}