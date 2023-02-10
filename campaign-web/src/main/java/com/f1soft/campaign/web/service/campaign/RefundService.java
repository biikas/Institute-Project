package com.f1soft.campaign.web.service.campaign;

import com.f1soft.campaign.common.dto.ServerResponse;
import com.f1soft.campaign.web.dto.request.ManualRedeemRequest;
import com.f1soft.campaign.web.dto.request.RedeemRequest;

/**
 * @author Prajwol Hada
 */
public interface RefundService {

    ServerResponse redeem(RedeemRequest refundRequest);

    ServerResponse acceptManualRedeem(ManualRedeemRequest redeemRequest);

    ServerResponse failRedeem(ManualRedeemRequest redeemRequest);

    ServerResponse redeemAll();

}
