package com.f1soft.campaign.web.refund;


import com.f1soft.campaign.common.dto.ServerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RedeemContext {

    @Autowired
    private RedeemModeFactory redeemFactory;

    public ServerResponse redeem(List<Long> offerTransactionIdList) {
        return redeemFactory.getRedeemOperations().redeem(offerTransactionIdList);
    }


}