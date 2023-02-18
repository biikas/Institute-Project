package com.college.campaign.common.cbs.query.executor;

import com.college.campaign.common.cbs.dto.TransactionDTO;

import java.util.List;

/**
 * @author Shreetika Panta
 */
public interface CampaignTxnDetailDAO {

    List<TransactionDTO> campaignTxnDetail(String query);

    List<TransactionDTO> campaignTxnDetailWithoutProduct(String query);
}
