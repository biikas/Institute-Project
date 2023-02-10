package com.f1soft.campaign.transaction.fund.transfer;

import com.f1soft.campaign.common.constant.MsgConstant;
import com.f1soft.campaign.common.dto.Message;
import com.f1soft.campaign.common.dto.ServerResponse;
import com.f1soft.campaign.common.enums.OfferTransactionStatusEnum;
import com.f1soft.campaign.common.exception.InvalidDataException;
import com.f1soft.campaign.common.helper.CampaignTotalRedeemUpdater;
import com.f1soft.campaign.common.util.MessageComposer;
import com.f1soft.campaign.common.util.ResponseMsg;
import com.f1soft.campaign.entities.model.OfferTransaction;
import com.f1soft.campaign.entities.model.RequestInfo;
import com.f1soft.campaign.repository.RequestInfoRepository;
import com.f1soft.campaign.transaction.constant.RedeemConstant;
import com.f1soft.campaign.transaction.dto.RedeemRequestData;
import com.f1soft.campaign.transaction.mapper.RequestInfoMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Prajwol Hada
 */
@Slf4j
@Component
public abstract class Redeem {

    @Autowired
    private RequestInfoRepository requestInfoRepository;
    @Autowired
    private CampaignTotalRedeemUpdater campaignTotalRedeemUpdater;

    protected RedeemRequestData fundTransferRequestData;

    private RequestInfo requestInfo;


    private void init(RedeemRequestData fundTransferRequestData) {
        this.fundTransferRequestData = fundTransferRequestData;
    }

    public ServerResponse doPayment(RedeemRequestData fundTransferRequestData) {

        init(fundTransferRequestData);
        ServerResponse serverResponse = validateRequest();
        if (serverResponse.isSuccess()) {
            if (serverResponse.isSuccess()) {
                serverResponse = campaignTotalRedeemUpdater.validateAmountLimit(fundTransferRequestData.getCampaign(), fundTransferRequestData.getAmount());
                if (serverResponse.isSuccess()) {
                    serverResponse = recordRequest();
                    if (serverResponse.isSuccess()) {
                        this.requestInfo = (RequestInfo) serverResponse.getObj();
                        try {
                            serverResponse = this.pay(requestInfo);
                        } catch (Exception e) {
                            log.error("Error : ", e);
                            return handlePayException();
                        }
                    }
                }
            }
        }
        return serverResponse;
    }

    private ServerResponse validateRequest() {
        ServerResponse serverResponse = new ServerResponse();

        OfferTransaction offerTransaction = fundTransferRequestData.getOfferTransaction();
        if (!offerTransaction.getStatus().equalsIgnoreCase("ACTIVE")) {
            throw new InvalidDataException(ResponseMsg.failureResponse(MsgConstant.INVALID_DATA));
        }
        if (!offerTransaction.getTransactionStatus().equalsIgnoreCase(OfferTransactionStatusEnum.PENDING.name())) {
            throw new InvalidDataException(ResponseMsg.failureResponse(MsgConstant.INVALID_DATA));
        }
        if (fundTransferRequestData.getOfferTransaction().getCampaignOffer().getOfferMode().getCode().equalsIgnoreCase(RedeemConstant.CASHBACK)
                || fundTransferRequestData.getOfferTransaction().getCampaignOffer().getOfferMode().getCode().equalsIgnoreCase(RedeemConstant.CASHBACK)) {
            if (fundTransferRequestData.getAmount() <= 0) {
                throw new InvalidDataException(ResponseMsg.failureResponse(MsgConstant.INVALID_AMOUNT));
            }
        }

        serverResponse.setSuccess(true);
        return serverResponse;
    }

    private ServerResponse recordRequest() {
        try {
            ServerResponse serverResponse = new ServerResponse();

            RequestInfo requestInfo = RequestInfoMapper.convertToRequestInfo(fundTransferRequestData.getApplicationUser(), fundTransferRequestData.getOfferTransaction(),
                    fundTransferRequestData.getCampaign());

            requestInfoRepository.save(requestInfo);

            serverResponse.setObj(requestInfo);
            serverResponse.setSuccess(true);
            return serverResponse;
        } catch (Exception e) {
            log.error("Exception ", e);
            throw new RuntimeException("System error");
        }
    }

    protected abstract ServerResponse pay(RequestInfo requestInfo);


    protected ServerResponse handlePayException() {
        return buildExceptionMessage();
    }

    private ServerResponse buildExceptionMessage() {
        ServerResponse serverResponse = new ServerResponse();

        Message msg = MessageComposer.compose(MsgConstant.FundTransfer.REFUND_FAILURE);

        serverResponse.setSuccess(false);

        serverResponse.setMessage(msg.getMessage());
        return serverResponse;
    }
}
