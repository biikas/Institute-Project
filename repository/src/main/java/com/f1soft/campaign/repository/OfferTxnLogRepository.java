package com.f1soft.campaign.repository;

import com.f1soft.campaign.entities.model.OfferTransaction;
import com.f1soft.campaign.entities.model.OfferTxnLog;
import com.f1soft.campaign.repository.custom.OfferTransactionCustom;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author Prajwol hada
 */
public interface OfferTxnLogRepository extends BaseRepository<OfferTxnLog> {

}
