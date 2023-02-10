package com.f1soft.campaign.repository.custom.impl;

import com.f1soft.campaign.entities.model.OfferTransaction;
import com.f1soft.campaign.entities.model.QOfferTransaction;
import com.f1soft.campaign.repository.Util.FieldQueryParameter;
import com.f1soft.campaign.repository.Util.QueryMapper;
import com.f1soft.campaign.repository.Util.SearchField;
import com.f1soft.campaign.repository.Util.SearchParameter;
import com.f1soft.campaign.repository.custom.OfferTransactionCustom;
import com.f1soft.campaign.repository.querydsl.AbstractQueryDslBuilder;
import com.google.common.collect.ImmutableMap;
import com.querydsl.core.BooleanBuilder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Map;

/**
 * @author Rashim Dhaubanjar
 */
@Repository
@Transactional
public class OfferTransactionRepositoryImpl extends AbstractQueryDslBuilder implements OfferTransactionCustom {

    @PersistenceContext
    private EntityManager em;

    protected Map<String, SearchField> search = ImmutableMap.<String, SearchField>builder()
            .put("fromDate", new SearchField(offerTransaction.claimDate, ">", "&&"))
            .put("toDate", new SearchField(offerTransaction.claimDate, "<", "&&"))
            .put("mobileNumber", new SearchField(offerTransaction.mobileNumber, "==", "&&"))
            .put("txnStatus", new SearchField(offerTransaction.transactionStatus, "==", "&&"))
            .put("campaignName", new SearchField(offerTransaction.campaign.title, "::", "&&"))
            .put("campaignMode", new SearchField(offerTransaction.campaign.campaignMode, "==", "&&"))
            .put("campaignId", new SearchField(offerTransaction.campaign.id, "==", "&&"))
            .build();

    @Override
    public BooleanBuilder searchQuery(List<FieldQueryParameter> fieldQueryParameterList) {
        List<SearchParameter> searchParameterList = QueryMapper.convertToSearchParameterList(fieldQueryParameterList, search);
        BooleanBuilder booleanBuilder = new BooleanBuilder();

        return buildWhereClause(searchParameterList, booleanBuilder);
    }

    @Override
    public List<OfferTransaction> getOfferTransactionsToProcessForRedeem(int recordToFetch) {
        Query query = em.createQuery("select t from OfferTransaction t where t.status = 'ACTIVE' and t.transactionStatus = 'PENDING' and t.campaign.campaignMode='AUTO'");
        query.setMaxResults(recordToFetch);
        return query.getResultList();
    }


}
