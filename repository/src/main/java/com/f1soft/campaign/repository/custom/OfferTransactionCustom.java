package com.f1soft.campaign.repository.custom;

import com.f1soft.campaign.entities.model.OfferTransaction;
import com.f1soft.campaign.repository.Util.FieldQueryParameter;
import com.querydsl.core.BooleanBuilder;

import java.util.List;

/**
 * @author Sabrin Luitel
 */
public interface OfferTransactionCustom {

    BooleanBuilder searchQuery(List<FieldQueryParameter> fieldQueryParameterList);

    List<OfferTransaction> getOfferTransactionsToProcessForRedeem(int recordToFetch);

}
