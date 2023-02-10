package com.f1soft.campaign.web.service;

import com.f1soft.campaign.common.dto.ServerResponse;
import com.f1soft.campaign.repository.Util.SearchQueryParameter;
import org.apache.catalina.Server;

/**
 * @author Prajwol Hada
 */
public interface TransactionDataService {

    ServerResponse getAllTransactionList();

    ServerResponse searchTransaction(SearchQueryParameter searchQueryParameter);

    ServerResponse transactionDetail(Long id);

    ServerResponse searchManualTransaction(SearchQueryParameter searchQueryParameter);

    ServerResponse transactionDetailByCampaignId(Long campaignId);
}
