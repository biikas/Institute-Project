package com.f1soft.campaign.web.refund;

import com.f1soft.campaign.common.dto.ServerResponse;
import com.f1soft.campaign.web.dto.response.RedeemResponse;
import com.f1soft.campaign.web.refund.dto.JobResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


@Slf4j
@Component
public class RedeemExecutor {

    @Autowired
    private RedeemContext redeemContext;

    public JobResponse process(List<Long> offerTransactionIdList) {

        ServerResponse serverResponse = redeemContext.redeem(offerTransactionIdList);

        if (serverResponse.isSuccess()) {
            return new JobResponse(true, (RedeemResponse) serverResponse.getObj(), "Cash Back Success");
        } else {
            return new JobResponse(false, serverResponse.getMessage());
        }
    }
}
