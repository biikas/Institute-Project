package com.f1soft.campaign.web.mapper;

import com.f1soft.campaign.common.helper.IdHelper;
import com.f1soft.campaign.common.util.DateFormat;
import com.f1soft.campaign.common.util.DateFormatter;
import com.f1soft.campaign.entities.model.OfferTransaction;
import com.f1soft.campaign.web.dto.OfferTransactionListResponse;
import com.f1soft.campaign.web.dto.OfferTransactionResponse;
import com.f1soft.campaign.web.dto.TransactionDetailResponse;

import java.util.List;
import java.util.stream.Collectors;

public class OfferTransactionMapper {

    public static OfferTransactionListResponse convertToOfferTransaction(List<OfferTransaction> offerTransactions) {
        OfferTransactionListResponse offerTransactionListResponse = new OfferTransactionListResponse();

        List<OfferTransactionResponse> offerTransactionList = offerTransactions
                .stream()
                .map(OfferTransactionMapper::convertToOfferTransactionResponse)
                .collect(Collectors.toList());
        offerTransactionListResponse.setTransactionList(offerTransactionList);
        return offerTransactionListResponse;
    }

    public static OfferTransactionResponse convertToOfferTransactionResponse(OfferTransaction offerTransaction) {

        OfferTransactionResponse offerTransactionResponse = new OfferTransactionResponse();
        offerTransactionResponse.setEid(IdHelper.getStringValue(offerTransaction.getId()));
        offerTransactionResponse.setMobileNumber(offerTransaction.getMobileNumber());
        offerTransactionResponse.setClaimDate(DateFormatter.convertToString(offerTransaction.getClaimDate(), DateFormat.DATE_FORMAT));
        offerTransactionResponse.setAmount(offerTransaction.getAmount());
        offerTransactionResponse.setOfferTxnCode(offerTransaction.getPromoCode());
        offerTransactionResponse.setTxnCode(offerTransaction.getServiceCode());
        offerTransactionResponse.setStatus(offerTransaction.getStatus());
        offerTransactionResponse.setChannel(offerTransaction.getChannel().getName());
        offerTransactionResponse.setRemarks(offerTransaction.getRemarks());
        offerTransactionResponse.setOfferType(offerTransaction.getCampaignOffer().getOfferMode().getName());
        offerTransactionResponse.setTxnStatus(offerTransaction.getTransactionStatus());
        offerTransactionResponse.setCampaignMode(offerTransaction.getCampaign().getCampaignMode());
        if (offerTransaction.getTransactionDate() != null) {
            offerTransactionResponse.setTxnDate(DateFormatter.convertToString(offerTransaction.getTransactionDate(), DateFormat.DATE_FORMAT));
        }
        offerTransactionResponse.setCampaignName(offerTransaction.getCampaign().getTitle());
        offerTransactionResponse.setTxnAmount(offerTransaction.getTransactionAmount());
        if(offerTransaction.getCustomerName() != null) {
            offerTransactionResponse.setCustomerName(offerTransaction.getCustomerName());
        }
        return offerTransactionResponse;
    }

    public static TransactionDetailResponse convertToTransactionDetailResponse(OfferTransaction offerTransaction) {
        TransactionDetailResponse transactionDetailResponse = new TransactionDetailResponse();

        transactionDetailResponse.setId(offerTransaction.getId());
        transactionDetailResponse.setTransactionId(offerTransaction.getTransactionId());
        transactionDetailResponse.setMobileNumber(offerTransaction.getMobileNumber());
        transactionDetailResponse.setRecordedDate(DateFormatter.convertToString(offerTransaction.getRecordedDate()));
        transactionDetailResponse.setTransactionDate(DateFormatter.convertToString(offerTransaction.getTransactionDate()));
        transactionDetailResponse.setPromoCode(offerTransaction.getPromoCode());
        transactionDetailResponse.setAmount(offerTransaction.getAmount());
        transactionDetailResponse.setTxnAmount(offerTransaction.getTransactionAmount());
        transactionDetailResponse.setServiceName(offerTransaction.getServiceName());
        transactionDetailResponse.setAccountNumber(offerTransaction.getAccountNumber());

        return transactionDetailResponse;
    }
}
