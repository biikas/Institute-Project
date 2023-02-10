package com.f1soft.campaign.repository;

import com.f1soft.campaign.entities.model.OfferTransaction;
import com.f1soft.campaign.repository.custom.OfferTransactionCustom;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author Prajwol hada
 */
public interface OfferTransactionRepository extends BaseRepository<OfferTransaction>, OfferTransactionCustom {

    @Query("select t from OfferTransaction t order by t.id desc")
    List<OfferTransaction> getAllOfferList();

    @Query("select t from OfferTransaction  t where t.campaign.campaignMode = 'MANUAL' and t.transactionStatus = 'PENDING' and t.status = 'ACTIVE'")
    List<OfferTransaction> getAllManualOfferTransactions();

    @Query("select t from OfferTransaction t where t.campaign.id =:campaignId order by t.id desc")
    List<OfferTransaction> getOfferTransactionByCampaignId(Long campaignId);
}
