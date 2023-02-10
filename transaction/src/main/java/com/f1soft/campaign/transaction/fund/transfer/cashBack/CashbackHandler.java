package com.f1soft.campaign.transaction.fund.transfer.cashBack;

import com.f1soft.campaign.entities.model.RequestInfo;
import com.f1soft.campaign.transaction.builder.IsoMapBuilder;
import com.f1soft.campaign.transaction.constant.TxnConstant;
import com.f1soft.campaign.transaction.dto.RedeemRequestData;
import com.f1soft.campaign.transaction.dto.IsoDescriptionParameter;
import com.f1soft.campaign.transaction.dto.TxnParameter;
import com.f1soft.campaign.transaction.helper.PaymentMapExtractor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class CashbackHandler {

    @Autowired
    private PaymentMapExtractor paymentMapExtractor;
//    @Autowired
//    private CommissionRepository commissionRepository;

    public TxnParameter buildParameters(RedeemRequestData fundTransferRequestData, RequestInfo requestInfo) {
        String fromAc = fundTransferRequestData.getAccountNumber();
        String toAc = fundTransferRequestData.getToAccount();
        Double amount = fundTransferRequestData.getAmount();
        String remarks = fundTransferRequestData.getRemarks();

        IsoDescriptionParameter isoDescriptionParameter = new IsoDescriptionParameter.IsoDescriptionParameterBuilder()
                .initiator(fundTransferRequestData.getApplicationUser().getUsername())
                .traceId(String.valueOf(requestInfo.getId()))
//                .reversalId(String.valueOf(fundTransferRequestData.getReversalId()))
                .paymentAttribute(toAc)
                .remarks(remarks)
                .fromAccount(fromAc)
                .toAccount(toAc)
                .promoCode(requestInfo.getCampaign().getPromoCode())
                .build();


        Map<String, String> isoMap = new IsoMapBuilder().build(paymentMapExtractor.fetchIsoParam("DEFAULT"),
                new HashMap<>(), isoDescriptionParameter);

        log.debug("Iso map for fund transfer : {}", isoMap);
        TxnParameter txnParameter = new TxnParameter();
        txnParameter.setFromAccount(fromAc);
        txnParameter.setToAccount(toAc);
        txnParameter.setInitiator(fundTransferRequestData.getApplicationUser().getUsername());
        txnParameter.setIsoMap(isoMap);
        txnParameter.setAmount(amount);
        txnParameter.setExtraCharge(TxnConstant.FP_NO_EXTRA_CHARGE);
        txnParameter.setExtraChargeAmount(0D);
        txnParameter.setRemarks(remarks);
//        txnParameter.setCommissionInfo(CommissionMapper.convertToCommissionInfo(
//                commissionRepository.findByFeatureId(fundTransferRequestData.getRequestedFeature().getId())));
        txnParameter.setRequestInfo(requestInfo);
//        txnParameter.setBranchInfoDTO(fundTransferRequestData.getTransferDetail().getBranchDetail());
        txnParameter.setServiceCode("ATAT");

        return txnParameter;
    }
}