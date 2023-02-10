package com.f1soft.campaign.transaction.fund.transfer;

import com.f1soft.campaign.entities.model.OfferTransaction;
import com.f1soft.campaign.transaction.constant.RedeemConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;


/**
 * @author Bikash Shah
 */
@Component
public class RedeemFactory {

    @Autowired
    @Qualifier("topUpPayment")
    private Redeem topUp;

    @Autowired
    @Qualifier("cashBackPayment")
    private Redeem cashBack;

    @Autowired
    @Qualifier("dataPack")
    private Redeem dataPack;

    @Autowired
    @Qualifier("accountLoad")
    private Redeem accountLoad;

    public Redeem getRedeemService(OfferTransaction offerTransaction) {
        String code = offerTransaction.getCampaignOffer().getOfferMode().getCode();
        if (code.equals(RedeemConstant.TOP_UP)) {
            return topUp;
        } else if (code.equals(RedeemConstant.CASHBACK)) {
            return cashBack;
        } else if (code.equals(RedeemConstant.DATAPACK)) {
            return dataPack;
        } else if (code.equals(RedeemConstant.ACCOUNT_LOAD)) {
            return accountLoad;
        } else {
            return null;
        }
    }
}
