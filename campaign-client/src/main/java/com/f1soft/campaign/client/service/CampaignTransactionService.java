package com.f1soft.campaign.client.service;

import com.f1soft.campaign.common.dto.ServerResponse;
import com.f1soft.campaign.client.dto.request.TransactionBookingRequest;
import com.f1soft.campaign.client.dto.request.TransactionRequest;

/**
 * @author Prajwol hada
 */
public interface CampaignTransactionService {

    ServerResponse bookOffer(TransactionBookingRequest transactionBookingRequest);

    ServerResponse offerPayment(TransactionRequest transactionRequest);
}
