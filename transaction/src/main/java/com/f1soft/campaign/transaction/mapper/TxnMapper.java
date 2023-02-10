package com.f1soft.campaign.transaction.mapper;

import com.f1soft.campaign.entities.model.IsoTxnRequest;
import com.f1soft.campaign.entities.model.RequestInfo;
import com.f1soft.campaign.transaction.constant.TxnConstant;
import com.f1soft.campaign.transaction.util.AmountFormatter;

import java.util.Date;

public class TxnMapper {

    private TxnMapper() {
    }

    public static IsoTxnRequest convertToIsoTxnRequest(String fromAccount, String toAccount, Double amount, RequestInfo requestInfo) {
        IsoTxnRequest isoRequest = new IsoTxnRequest();
        isoRequest.setFromAccount(fromAccount);
        isoRequest.setToAccount(toAccount);
        isoRequest.setAmount(AmountFormatter.doubleToBigDecimal(amount));
        isoRequest.setRequestInfo(requestInfo);
        isoRequest.setCommissionInvolved('N');
        isoRequest.setTxnStatus(TxnConstant.FUND_TO_TRANSFER);
        isoRequest.setReversed('N');
        isoRequest.setTxnDate(new Date());
        return isoRequest;
    }

//    public static CommissionCalculation convertToCommissionCalculation(CommissionResponse commission, CommissionInformation cInformation, RequestInfo requestInfo) {
//
//        CommissionCalculation commissionCalculation = new CommissionCalculation();
//        commissionCalculation.setAmount(AmountFormatter.doubleToBigDecimal(commission.getPaidAmount()));
//        commissionCalculation.setTotalCommission(AmountFormatter.doubleToBigDecimal(commission.getCommission()));
//        commissionCalculation.setCalculatedCommissionRate(commission.getCommissionRate());
//        commissionCalculation.setCalculatedCommissionType(commission.getCommissionType());
//        commissionCalculation.setRealTimeSettled(commission.getRealTimeSettled());
//
//        commissionCalculation.setCommissionInformation(cInformation);
//        commissionCalculation.setRequestInfo(requestInfo);
//        commissionCalculation.setCommissionPayable(commission.getCommissionPayee());
//        return commissionCalculation;
//    }

//    public static List<PartnerCommissionCalculation> convertToPartnerCommissionCalculation(CommissionResponse commission, CommissionCalculation commissionCalculation) {
//
//        List<CommissionResponse> partnerCommission = commission.getPartnerResponses();
//
//        List<PartnerCommissionCalculation> partnerCommissionCalculationList = new ArrayList<>();
//        for (CommissionResponse pCommission : partnerCommission) {
//            PartnerCommissionCalculation partnerCommissionCalculation = new PartnerCommissionCalculation();
//            CommissionSharing commissionSharing = pCommission.getCommissinSharing();
//            partnerCommissionCalculation.setCalculatedCommission(AmountFormatter.doubleToBigDecimal(pCommission.getCommission()));
//            partnerCommissionCalculation.setCalculatedCommissionRate(pCommission.getCommissionRate());
//            partnerCommissionCalculation.setCalculatedCommissionType(pCommission.getCommissionType());
//            partnerCommissionCalculation.setCommissionSharing(commissionSharing);
//            partnerCommissionCalculation.setCommissionCalculation(commissionCalculation);
//            partnerCommissionCalculationList.add(partnerCommissionCalculation);
//        }
//        return partnerCommissionCalculationList;
//    }
}
