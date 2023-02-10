package com.f1soft.campaign.client.service.impl;

import com.f1soft.campaign.client.dto.TransactionRequesterData;
import com.f1soft.campaign.client.dto.request.TransactionBookingRequest;
import com.f1soft.campaign.client.dto.request.TransactionRequest;
import com.f1soft.campaign.client.service.CampaignTransactionService;
import com.f1soft.campaign.client.transaction.OfferBooking;
import com.f1soft.campaign.client.transaction.OfferPayment;
import com.f1soft.campaign.common.constant.MsgConstant;
import com.f1soft.campaign.common.dto.ServerResponse;
import com.f1soft.campaign.common.exception.InvalidDataException;
import com.f1soft.campaign.common.util.ResponseMsg;
import com.f1soft.campaign.entities.model.BookingRequest;
import com.f1soft.campaign.entities.model.Campaign;
import com.f1soft.campaign.entities.model.CampaignEligibleUser;
import com.f1soft.campaign.repository.BookingRequestRepository;
import com.f1soft.campaign.repository.CampaignEligibleUserRepository;
import com.f1soft.campaign.repository.CampaignRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author Prajwol hada
 */
@Slf4j
@Service
public class CampaignTransactionServiceImpl implements CampaignTransactionService {

    @Autowired
    private CampaignRepository campaignRepository;

    @Autowired
    private BookingRequestRepository bookingRequestRepository;

    @Autowired
    private OfferBooking offerBooking;

    @Autowired
    private OfferPayment offerPayment;

    @Autowired
    private CampaignEligibleUserRepository campaignEligibleUserRepository;

    @Override
    public ServerResponse bookOffer(TransactionBookingRequest transactionBookingRequest) {

        Optional<Campaign> campaignOptional = campaignRepository.getCampaignByPromoCode(transactionBookingRequest.getPromoCode());

        Optional<CampaignEligibleUser> campaignEligibleUserOptional = campaignEligibleUserRepository.findCampaignEligibleUserByUserNameAndPromoCodeAndCampaignId(transactionBookingRequest.getMobileNumber(), transactionBookingRequest.getPromoCode());

        TransactionRequesterData transactionRequesterData = null;
        if (campaignOptional.isPresent()) {
            transactionRequesterData = TransactionRequesterData.builder()
                    .campaign(campaignOptional.get())
                    .mobileNumber(transactionBookingRequest.getMobileNumber())
                    .serviceCode(transactionBookingRequest.getServiceCode())
                    .channel(transactionBookingRequest.getChannel())
                    .transactionAmount(transactionBookingRequest.getAmount())
                    .profileId(transactionBookingRequest.getCustomerProfileId())
                    .emailAddress(transactionBookingRequest.getEmailAddress())
                    .customerName(transactionBookingRequest.getCustomerName())
                    .build();
        } else {
            if (campaignEligibleUserOptional.isPresent()) {
                transactionRequesterData = TransactionRequesterData.builder()
                        .campaign(campaignEligibleUserOptional.get().getCampaign())
                        .mobileNumber(transactionBookingRequest.getMobileNumber())
                        .serviceCode(transactionBookingRequest.getServiceCode())
                        .channel(transactionBookingRequest.getChannel())
                        .transactionAmount(transactionBookingRequest.getAmount())
                        .profileId(transactionBookingRequest.getCustomerProfileId())
                        .emailAddress(transactionBookingRequest.getEmailAddress())
                        .customerName(transactionBookingRequest.getCustomerName())
                        .build();
            } else {
                throw new InvalidDataException(ResponseMsg.failureResponse(MsgConstant.Customer.INVALID_PROMO_CODE));
            }
        }
        return offerBooking.doBooking(transactionRequesterData);
    }

    @Override
    public ServerResponse offerPayment(TransactionRequest transactionRequest) {

        Optional<BookingRequest> bookingRequestOptional = bookingRequestRepository.findByBookingId(transactionRequest.getBookingId());
        if (bookingRequestOptional.isPresent()) {
            TransactionRequesterData transactionRequesterData = TransactionRequesterData.builder()
                    .campaign(bookingRequestOptional.get().getCampaign())
                    .mobileNumber(bookingRequestOptional.get().getMobileNumber())
                    .serviceCode(bookingRequestOptional.get().getServiceCode())
                    .channel(bookingRequestOptional.get().getChannel().getCode())
                    .bookingId(transactionRequest.getBookingId())
                    .transactionAmount(bookingRequestOptional.get().getTxnAmount())
                    .transactionId(transactionRequest.getTransactionId())
                    .accountNumber(transactionRequest.getAccountNumber())
                    .bookingRequest(bookingRequestOptional.get())
                    .profileId(bookingRequestOptional.get().getProfileId())
                    .transactionAmount(transactionRequest.getAmount())
                    .emailAddress(bookingRequestOptional.get().getEmailAddress())
                    .customerName(bookingRequestOptional.get().getCustomerName())
                    .build();

            return offerPayment.pay(transactionRequesterData);
        } else {
            throw new InvalidDataException(ResponseMsg.failureResponse(MsgConstant.Customer.INVALID_DATA));
        }
    }
}
