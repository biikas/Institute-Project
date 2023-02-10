package com.f1soft.campaign.common.cbs.query.executor;

import com.f1soft.campaign.common.cbs.dto.CustomerDetailDTO;
import com.f1soft.campaign.common.cbs.dto.TransactionDTO;

import java.util.List;

/**
 * @author Shreetika Panta
 */
public interface CampaignTxnDetailDAO {

    List<TransactionDTO> campaignTxnDetail(String query);

    List<TransactionDTO> campaignTxnDetailWithoutProduct(String query);
}
