package com.f1soft.campaign.client.transaction;

import com.f1soft.campaign.client.constant.Constant;
import com.f1soft.campaign.client.dto.TransactionRequesterData;
import com.f1soft.campaign.client.dto.UserInfoDTO;
import com.f1soft.campaign.client.dto.response.OfferTransactionResponse;
import com.f1soft.campaign.client.manager.UserGiftCardManager;
import com.f1soft.campaign.client.mapper.GiftCardUserMapper;
import com.f1soft.campaign.client.mapper.TransactionMapper;
import com.f1soft.campaign.client.mapper.UserInfoMapper;
import com.f1soft.campaign.common.constant.MsgConstant;
import com.f1soft.campaign.common.dto.ServerResponse;
import com.f1soft.campaign.common.enums.OfferModeEnum;
import com.f1soft.campaign.common.exception.InvalidDataException;
import com.f1soft.campaign.common.util.ResponseMsg;
import com.f1soft.campaign.entities.model.*;
import com.f1soft.campaign.repository.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import java.util.Date;

/**
 * @author Prajwol hada
 */
@Slf4j
@Component
@RequestScope
public class OfferPayment extends AbstractTransaction {

    @Autowired
    private BookingLogRepository bookingLogRepository;

    @Autowired
    private OfferTransactionRepository offerTransactionRepository;

    @Autowired
    private BookingRequestRepository bookingRequestRepository;

    @Autowired
    private ChannelRepository channelRepository;

    @Autowired
    private DataPackageCampaignUserRepository dataPackageCampaignUserRepository;

    @Autowired
    private GiftCardDetailRepository giftCardDetailRepository;

    @Autowired
    private GiftCardEligibleUserRepository giftCardEligibleUserRepository;

    @Autowired
    private UserGiftCardManager giftCardManager;

    private BookingLog bookingLog;

    public ServerResponse pay(TransactionRequesterData transactionRequesterData) {
        init(transactionRequesterData);
        ServerResponse serverResponse = isCampaignValid();
        if (serverResponse.isSuccess()) {
            serverResponse = validateCustomerProfile();
            if (serverResponse.isSuccess()) {
                serverResponse = validateChannel();
                if (serverResponse.isSuccess()) {
                    serverResponse = validateService();
                    if (serverResponse.isSuccess()) {
                        serverResponse = validateLimit();
                        if (serverResponse.isSuccess()) {
                            serverResponse = validateAmountLimit();
                            if (serverResponse.isSuccess()) {
                                serverResponse = validateRequest();
                                if (serverResponse.isSuccess()) {
                                    serverResponse = process();
                                }
                            }
                        }
                    }
                }
            }
        }
        return serverResponse;
    }

    private ServerResponse validateRequest() {
        ServerResponse serverResponse = new ServerResponse();

        BookingRequest bookingRequest = transactionRequesterData.getBookingRequest();

        if (bookingRequest.getStatus().equalsIgnoreCase(Constant.BookingStatus.PAID)) {
            throw new InvalidDataException(ResponseMsg.failureResponse(MsgConstant.Customer.CODE_ALREADY_USED));
        }

        if (new Date().compareTo(bookingRequest.getExpiryDate()) > 0) {
            throw new InvalidDataException(ResponseMsg.failureResponse(MsgConstant.Customer.CODE_EXPIRED));
        }

        this.bookingLog = bookingLogRepository.findByCampaign(campaign.getId()).get();

        if (bookingLog.getTotalUsed() >= campaign.getTotalIssued()) {
            throw new InvalidDataException(ResponseMsg.failureResponse(MsgConstant.Customer.NO_CODE_AVAILABLE));
        }

        if (!transactionRequesterData.getTransactionAmount().equals(bookingRequest.getTxnAmount())) {
            throw new InvalidDataException(ResponseMsg.failureResponse(MsgConstant.CAMPAIGN_AMOUNT_MISMATCH));
        }

        serverResponse.setSuccess(true);
        return serverResponse;
    }

    private ServerResponse process() {
        ServerResponse serverResponse = new ServerResponse();

        Channel channel = channelRepository.getChannelByCode(transactionRequesterData.getChannel()).get();
//        Service service = serviceRepository.getServiceByCode(transactionRequesterData.getServiceCode()).get();

        CampaignEligibleService campaignEligibleService = campaign.getCampaignEligibleServices().stream()
                .filter(service -> service.getActive() == 'Y')
                .filter(service -> service.getServiceCode().equalsIgnoreCase(transactionRequesterData.getServiceCode()))
                .findAny().get();

        OfferTransaction offerTransaction = TransactionMapper.convertToOfferTransaction(
                transactionRequesterData, channel, campaignEligibleService.getServiceName(), campaignEligibleService.getServiceCode()
        );

        offerTransactionRepository.save(offerTransaction);

        if (campaignOffer.getOfferMode().getCode().equalsIgnoreCase(OfferModeEnum.GIFTCARD.name())) {
            GiftCard giftCard = giftCardDetailRepository.getGiftCardDetailByCode(campaignOffer.getValue()).get();

            GiftCardEligibleUser giftCardEligibleUser = GiftCardUserMapper.mapToGiftCardEligibleUser(campaign,
                    offerTransaction, giftCard, transactionRequesterData.getMobileNumber());

            giftCardEligibleUserRepository.save(giftCardEligibleUser);

            UserInfoDTO userInfoDTO = UserInfoMapper.convertToUserInfoDTO(transactionRequesterData);
            giftCardManager.sendGiftCardEmail(userInfoDTO, giftCard);
        }

        DataPackageCampaignUser dataPackageCampaignUser = TransactionMapper.convertToDataPackageCampaignUser(offerTransaction, transactionRequesterData);
        if (dataPackageCampaignUser != null) {
            dataPackageCampaignUserRepository.save(dataPackageCampaignUser);
        }

        int totalUsed = bookingLog.getTotalUsed();
        bookingLog.setTotalUsed(totalUsed + 1);

        bookingLogRepository.save(bookingLog);

        BookingRequest bookingRequest = transactionRequesterData.getBookingRequest();
        bookingRequest.setStatus(Constant.BookingStatus.PAID);

        bookingRequestRepository.save(bookingRequest);

        OfferTransactionResponse offerTransactionResponse = new OfferTransactionResponse();
        offerTransactionResponse.setTxnAmount(offerTransaction.getTransactionAmount());
        offerTransactionResponse.setOfferAmount(offerTransaction.getAmount());
        offerTransactionResponse.setOfferModeName(offerTransaction.getCampaignOffer().getOfferMode().getName());
        offerTransactionResponse.setOfferModeCode(offerTransaction.getCampaignOffer().getOfferMode().getCode());
        offerTransactionResponse.setPromoCode(offerTransaction.getPromoCode());

        serverResponse.setSuccess(true);
        serverResponse.setObj(offerTransactionResponse);
        serverResponse.setMessage("Transaction with code successful.");

        return serverResponse;
    }
}
