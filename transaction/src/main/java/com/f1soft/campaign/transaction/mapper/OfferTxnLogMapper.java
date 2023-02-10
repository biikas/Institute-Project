package com.f1soft.campaign.transaction.mapper;

import com.f1soft.campaign.entities.model.OfferTransaction;
import com.f1soft.campaign.entities.model.OfferTxnLog;

import java.util.Date;

/**
 * @author Prajwol Hada
 */
public class OfferTxnLogMapper {

    public static OfferTxnLog convertToOfferTxnLog(OfferTransaction offerTransaction) {
        OfferTxnLog offerTxnLog = new OfferTxnLog();

        offerTxnLog.setAddedDate(new Date());
        offerTxnLog.setOfferTransaction(offerTransaction);

        return offerTxnLog;
    }
}
