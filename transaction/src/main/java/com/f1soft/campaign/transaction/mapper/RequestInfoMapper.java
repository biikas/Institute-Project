package com.f1soft.campaign.transaction.mapper;

import com.f1soft.campaign.entities.model.ApplicationUser;
import com.f1soft.campaign.entities.model.Campaign;
import com.f1soft.campaign.entities.model.OfferTransaction;
import com.f1soft.campaign.entities.model.RequestInfo;

import java.util.Date;

public class RequestInfoMapper {

    private RequestInfoMapper() {
    }

    public static RequestInfo convertToRequestInfo(ApplicationUser applicationUser,
                                                   OfferTransaction offerTransaction,
                                                   Campaign campaign) {
        RequestInfo requestInfo = new RequestInfo();
        requestInfo.setRequestedDate(new Date());
        requestInfo.setCampaign(campaign);
        requestInfo.setApplicationUser(applicationUser);
        requestInfo.setOfferTransaction(offerTransaction);

        return requestInfo;
    }

}
