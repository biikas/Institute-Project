package com.f1soft.campaign.transaction.fund.transfer.dataPack;

import com.f1soft.campaign.common.constant.MsgConstant;
import com.f1soft.campaign.common.constant.Parameter;
import com.f1soft.campaign.common.dto.ServerResponse;
import com.f1soft.campaign.common.enums.OfferTransactionStatusEnum;
import com.f1soft.campaign.common.util.MessageComposer;
import com.f1soft.campaign.common.util.ResponseMsg;
import com.f1soft.campaign.entities.model.*;
import com.f1soft.campaign.repository.OfferProductRepository;
import com.f1soft.campaign.repository.OfferTransactionRepository;
import com.f1soft.campaign.repository.OfferTxnLogRepository;
import com.f1soft.campaign.repository.VendorResponseRepository;
import com.f1soft.campaign.transaction.connector.transaction.dataPackage.DataPackageManager;
import com.f1soft.campaign.transaction.dto.request.CounterMerchantPaymentRequest;
import com.f1soft.campaign.transaction.fund.transfer.Redeem;
import com.f1soft.campaign.common.helper.CampaignTotalRedeemUpdater;
import com.f1soft.campaign.transaction.mapper.OfferTxnLogMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author Bikash Shah
 */
@Slf4j
@Component
@Qualifier("dataPack")
public class DataPack extends Redeem {

    @Autowired
    private OfferProductRepository offerProductRepository;
    @Autowired
    private DataPackageManager dataPackageManager;
    @Autowired
    private OfferTransactionRepository offerTransactionRepository;
    @Autowired
    private OfferTxnLogRepository offerTxnLogRepository;
    @Autowired
    private VendorResponseRepository vendorResponseRepository;
    @Autowired
    private CampaignTotalRedeemUpdater campaignTotalRedeemUpdater;

    @Override
    protected ServerResponse pay(RequestInfo requestInfo) {

        ServerResponse serverResponse = new ServerResponse();
        log.debug("Initiating Data-package");
        PackageItem packageItem = getPackageItem(requestInfo);
        CounterMerchantPaymentRequest paymentRequest = DataPackHandler.buildParameter(requestInfo, packageItem);

        serverResponse = dataPackageManager.processDataPackagePayment(paymentRequest);
        updateTransaction(serverResponse);
        updateVendorResponse(serverResponse, requestInfo);

        if (serverResponse.isSuccess()) {
            campaignTotalRedeemUpdater.updateCampaignTotalRedeem(requestInfo.getCampaign(), packageItem.getAmount());
            return ResponseMsg.successResponse(MsgConstant.Datapack.DATAPACK_SUCCESS,
                    MessageComposer.getParameters(Parameter.AMOUNT,
                            String.valueOf(fundTransferRequestData.getAmount()),
                            Parameter.MOBILE_NUMBER,
                            String.valueOf(fundTransferRequestData.getOfferTransaction().getMobileNumber()),
                            Parameter.ACCOUNT_NUMBER, fundTransferRequestData.getOfferTransaction().getAccountNumber()));
        }

        return serverResponse;
    }

    protected void updateTransaction(ServerResponse serverResponse) {
        OfferTransaction offerTransaction = fundTransferRequestData.getOfferTransaction();
        offerTransaction.setTransactionDate(new Date());
        offerTransaction.setStatus("COMPLETED");

        OfferTxnLog offerTxnLog = OfferTxnLogMapper.convertToOfferTxnLog(offerTransaction);
        if (serverResponse.isSuccess()) {
            offerTransaction.setTransactionStatus(OfferTransactionStatusEnum.SUCCESS.name());
            if (fundTransferRequestData.getRemarks() != null) {
                offerTransaction.setRemarks(fundTransferRequestData.getRemarks());
            } else {
                offerTransaction.setRemarks(OfferTransactionStatusEnum.SUCCESS.name());
            }
            offerTxnLog.setStatus(OfferTransactionStatusEnum.SUCCESS.name());
            offerTxnLog.setRemarks(OfferTransactionStatusEnum.SUCCESS.name());
        } else {
            offerTxnLog.setStatus(OfferTransactionStatusEnum.FAILED.name());
            offerTxnLog.setRemarks(serverResponse.getMessage());

            offerTransaction.setRemarks(serverResponse.getMessage());
            offerTransaction.setTransactionStatus(OfferTransactionStatusEnum.FAILED.name());

        }
        offerTransactionRepository.save(offerTransaction);
        offerTxnLogRepository.save(offerTxnLog);
    }

    public void updateVendorResponse(ServerResponse serverResponse, RequestInfo requestInfo) {
        VendorResponse vendorResponse = new VendorResponse();
        vendorResponse.setResponseCode(serverResponse.getCode());
        vendorResponse.setRequestInfo(requestInfo);
        vendorResponse.setResponseStatus(Integer.parseInt(serverResponse.getCode()));
        vendorResponse.setResponseDesc(serverResponse.getMessage());
        //request info id of bankxp
        if (serverResponse.getObj() != null) {
            vendorResponse.setTraceId((serverResponse.getObj().toString()));
        }
        vendorResponseRepository.save(vendorResponse);
    }

    private PackageItem getPackageItem(RequestInfo requestInfo) {
        PackageItem packageItem = requestInfo.getOfferTransaction().getDataPackageCampaignUser().getPackageItem();
        return packageItem;
    }

}
