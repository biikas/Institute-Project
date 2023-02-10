package com.f1soft.campaign.transaction.fund.transfer.dataPack;

import com.f1soft.campaign.entities.model.PackageItem;
import com.f1soft.campaign.entities.model.RequestInfo;
import com.f1soft.campaign.transaction.dto.request.CounterMerchantPaymentRequest;
import com.f1soft.campaign.transaction.dto.request.MerchantPaymentFieldRequest;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Bikash Shah
 */
public class DataPackHandler {

    public static CounterMerchantPaymentRequest buildParameter(RequestInfo requestInfo,PackageItem packageItem) {
        CounterMerchantPaymentRequest counterMerchantPaymentRequest = new CounterMerchantPaymentRequest();
        counterMerchantPaymentRequest.setAccountNumber(requestInfo.getCampaign().getOfferAccount());
        counterMerchantPaymentRequest.setApplicationUserId(requestInfo.getApplicationUser().getId());
        counterMerchantPaymentRequest.setMerchantCode(packageItem.getProductCode());
        counterMerchantPaymentRequest.setFields(convertToMerchantPaymentFieldRequest(requestInfo,packageItem));
        counterMerchantPaymentRequest.setUsername(requestInfo.getOfferTransaction().getMobileNumber());
        return counterMerchantPaymentRequest;
    }

    public static List<MerchantPaymentFieldRequest> convertToMerchantPaymentFieldRequest(RequestInfo redeemRequestData,PackageItem packageItem) {
        List<MerchantPaymentFieldRequest> merchantPaymentFieldRequestList = new ArrayList<>();
        MerchantPaymentFieldRequest merchantPaymentFieldRequest1 = new MerchantPaymentFieldRequest();
        merchantPaymentFieldRequest1.setLabel("amount");
        merchantPaymentFieldRequest1.setParamOrder(0);
        merchantPaymentFieldRequest1.setParamValue(String.valueOf(packageItem.getAmount()));
        merchantPaymentFieldRequestList.add(merchantPaymentFieldRequest1);
        MerchantPaymentFieldRequest merchantPaymentFieldRequest = new MerchantPaymentFieldRequest();
        merchantPaymentFieldRequest.setLabel("packageId");
        merchantPaymentFieldRequest.setParamOrder(1);
        merchantPaymentFieldRequest.setParamValue(packageItem.getCode());
        merchantPaymentFieldRequestList.add(merchantPaymentFieldRequest);
        MerchantPaymentFieldRequest merchantPaymentFieldRequest2 = new MerchantPaymentFieldRequest();
        merchantPaymentFieldRequest2.setLabel("mobileNumber");
        merchantPaymentFieldRequest2.setParamOrder(2);
        merchantPaymentFieldRequest2.setParamValue(redeemRequestData.getOfferTransaction().getMobileNumber());
        merchantPaymentFieldRequestList.add(merchantPaymentFieldRequest2);

        return merchantPaymentFieldRequestList;
    }
}
