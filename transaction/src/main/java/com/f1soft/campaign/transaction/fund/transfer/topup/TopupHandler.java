package com.f1soft.campaign.transaction.fund.transfer.topup;

import com.f1soft.campaign.transaction.dto.request.CounterMerchantPaymentRequest;
import com.f1soft.campaign.transaction.dto.request.MerchantPaymentFieldRequest;
import com.f1soft.campaign.entities.model.RequestInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Bikash Shah
 */
@Slf4j
@Component
public class TopupHandler {

    public static CounterMerchantPaymentRequest buildParameter(RequestInfo requestInfo,String merchantCode){
        CounterMerchantPaymentRequest counterMerchantPaymentRequest = new CounterMerchantPaymentRequest();
        counterMerchantPaymentRequest.setAccountNumber(requestInfo.getOfferTransaction().getAccountNumber());
        counterMerchantPaymentRequest.setApplicationUserId(requestInfo.getApplicationUser().getId());
        counterMerchantPaymentRequest.setMerchantCode(merchantCode);
        counterMerchantPaymentRequest.setFields(convertToMerchantPaymentFieldRequest(requestInfo));
        return counterMerchantPaymentRequest;
    }

    public static List<MerchantPaymentFieldRequest> convertToMerchantPaymentFieldRequest(RequestInfo redeemRequestData) {
        List<MerchantPaymentFieldRequest> merchantPaymentFieldRequestList = new ArrayList<>();
        MerchantPaymentFieldRequest merchantPaymentFieldRequest1 = new MerchantPaymentFieldRequest();
        merchantPaymentFieldRequest1.setLabel("amount");
        merchantPaymentFieldRequest1.setParamOrder(0);
        merchantPaymentFieldRequest1.setParamValue(String.valueOf(redeemRequestData.getOfferTransaction().getAmount()));
        merchantPaymentFieldRequestList.add(merchantPaymentFieldRequest1);
        MerchantPaymentFieldRequest merchantPaymentFieldRequest = new MerchantPaymentFieldRequest();
        merchantPaymentFieldRequest.setLabel("mobileNumber");
        merchantPaymentFieldRequest.setParamOrder(1);
        merchantPaymentFieldRequest.setParamValue(redeemRequestData.getOfferTransaction().getMobileNumber());
        merchantPaymentFieldRequestList.add(merchantPaymentFieldRequest);
        MerchantPaymentFieldRequest merchantPaymentFieldRequest2 = new MerchantPaymentFieldRequest();
        merchantPaymentFieldRequest2.setLabel("remarks");
        merchantPaymentFieldRequest2.setParamOrder(2);
        merchantPaymentFieldRequest2.setParamValue(redeemRequestData.getCampaign().getPromoCode());
        merchantPaymentFieldRequestList.add(merchantPaymentFieldRequest2);

        return merchantPaymentFieldRequestList;
    }
}
