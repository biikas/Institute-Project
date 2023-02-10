package com.f1soft.campaign.client.transaction;

import com.f1soft.campaign.client.dto.TransactionRequesterData;
import com.f1soft.campaign.client.util.BookingHelper;
import com.f1soft.campaign.common.constant.MsgConstant;
import com.f1soft.campaign.common.constant.Parameter;
import com.f1soft.campaign.common.dto.ServerResponse;
import com.f1soft.campaign.common.enums.UserTypesEnum;
import com.f1soft.campaign.common.exception.IllegalStateException;
import com.f1soft.campaign.common.exception.InvalidDataException;
import com.f1soft.campaign.common.helper.CampaignTotalRedeemUpdater;
import com.f1soft.campaign.common.util.MessageComposer;
import com.f1soft.campaign.common.util.ResponseMsg;
import com.f1soft.campaign.entities.model.*;
import com.f1soft.campaign.repository.CampaignEligibleProfileRepository;
import com.f1soft.campaign.repository.CampaignTotalRedeemRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @author Prajwol hada
 */
@Slf4j
@Component
@RequestScope
public abstract class AbstractTransaction {

    @Autowired
    private CampaignEligibleProfileRepository campaignEligibleProfileRepository;
    @Autowired
    private CampaignTotalRedeemRepository campaignTotalRedeemRepository;
    @Autowired
    private CampaignTotalRedeemUpdater campaignTotalRedeemUpdater;

    protected TransactionRequesterData transactionRequesterData;
    protected Campaign campaign;
    protected CampaignOffer campaignOffer;

    protected void init(TransactionRequesterData transactionRequesterData) {
        this.transactionRequesterData = transactionRequesterData;
        this.campaign = transactionRequesterData.getCampaign();
    }

    protected ServerResponse validateChannel() {
        ServerResponse serverResponse = new ServerResponse();
        List<CampaignAllowedChannel> campaignAllowedChannelList = campaign.getCampaignAllowedChannels();

        Optional<CampaignAllowedChannel> campaignAllowedChannel = campaignAllowedChannelList.stream().filter(channel -> channel.getActive() == 'Y').filter(channel -> channel.getChannel().getCode().equalsIgnoreCase(transactionRequesterData.getChannel())).findAny();
        if (campaignAllowedChannel.isPresent()) {
            serverResponse.setSuccess(true);
        } else {
            throw new InvalidDataException(ResponseMsg.failureResponse(MsgConstant.Customer.INVALID_CHANNEL));
        }
        return serverResponse;
    }

    protected ServerResponse validateService() {
        ServerResponse serverResponse = new ServerResponse();
        List<CampaignEligibleService> campaignEligibleServices = campaign.getCampaignEligibleServices();

        Optional<CampaignEligibleService> campaignEligibleService = campaignEligibleServices.stream().filter(service -> service.getActive() == 'Y').filter(service -> service.getServiceCode().equalsIgnoreCase(transactionRequesterData.getServiceCode())).findAny();

        if (campaignEligibleService.isPresent()) {
            serverResponse.setSuccess(true);
        } else {
            throw new InvalidDataException(ResponseMsg.failureResponse(MsgConstant.Customer.INVALID_SERVICE));
        }
        return serverResponse;
    }

    protected ServerResponse validateLimit() {
        ServerResponse serverResponse = new ServerResponse();

        Double amount = transactionRequesterData.getTransactionAmount();
        for (CampaignOffer campaignOffer : campaign.getCampaignOffer()) {
            if (campaignOffer.getActive() == 'Y') {
                if (amount >= campaignOffer.getMinAmount() && amount <= campaignOffer.getMaxAmount()) {
                    serverResponse.setSuccess(true);
                    this.campaignOffer = campaignOffer;
                    return serverResponse;
                }
            }
        }
        throw new InvalidDataException(ResponseMsg.failureResponse(MsgConstant.Customer.INVALID_AMOUNT, MessageComposer.getParameters(Parameter.AMOUNT, String.valueOf(amount))));
    }

    protected ServerResponse isCampaignValid() {
        ServerResponse serverResponse = new ServerResponse();

        if (!campaign.getStatus().equalsIgnoreCase("ACTIVE")) {
            throw new IllegalStateException(ResponseMsg.failureResponse(MsgConstant.Customer.INVALID_PROMO_CODE));
        }
        if (campaign.getStartDate().after(new Date())) {
            throw new IllegalStateException(ResponseMsg.failureResponse(MsgConstant.Customer.OFFER_NOT_STARTED));
        } else if (campaign.getEndDate().before(new Date())) {
            throw new IllegalStateException(ResponseMsg.failureResponse(MsgConstant.Customer.OFFER_ALREADY_ENDED));
        }
        serverResponse.setSuccess(true);
        return serverResponse;
    }

    protected ServerResponse validateCustomerProfile() {
        ServerResponse serverResponse = new ServerResponse();

        if (campaign.getAllowedUsers().equalsIgnoreCase(UserTypesEnum.PROFILE.name())) {
            campaignEligibleProfileRepository.getByCampaignAndProfileId(campaign.getId(), this.transactionRequesterData.getProfileId()).orElseThrow(() -> new IllegalStateException(ResponseMsg.failureResponse(MsgConstant.Customer.INVALID_PROFILE)));
        }
        serverResponse.setSuccess(true);
        return serverResponse;
    }

    protected ServerResponse validateAmountLimit() {

        Double cashBackAmount = BookingHelper.calculateAmount(transactionRequesterData.getTransactionAmount(), campaign, transactionRequesterData.getMobileNumber());

        ServerResponse serverResponse = campaignTotalRedeemUpdater.validateAmountLimit(campaign, cashBackAmount);

        serverResponse.setSuccess(true);
        return serverResponse;
    }
}
