package com.f1soft.campaign.client.transaction;

import com.f1soft.campaign.client.constant.Constant;
import com.f1soft.campaign.client.dto.TransactionRequesterData;
import com.f1soft.campaign.client.dto.response.OfferBookResponse;
import com.f1soft.campaign.client.mapper.BookingMapper;
import com.f1soft.campaign.common.constant.MsgConstant;
import com.f1soft.campaign.common.dto.ServerResponse;
import com.f1soft.campaign.common.exception.IllegalStateException;
import com.f1soft.campaign.common.util.DateHelper;
import com.f1soft.campaign.common.util.ResponseMsg;
import com.f1soft.campaign.entities.model.BookingLog;
import com.f1soft.campaign.entities.model.BookingRequest;
import com.f1soft.campaign.entities.model.Channel;
import com.f1soft.campaign.repository.BookingLogRepository;
import com.f1soft.campaign.repository.BookingRequestRepository;
import com.f1soft.campaign.repository.ChannelRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Prajwol hada
 */
@Slf4j
@Component
@RequestScope
public class OfferBooking extends AbstractTransaction {

    @Autowired
    private BookingLogRepository bookingLogRepository;

    @Autowired
    private BookingRequestRepository bookingRequestRepository;

    @Autowired
    private ChannelRepository channelRepository;

    private List<BookingRequest> bookingRequests;

    private Optional<BookingLog> bookingLogOptional;

    public ServerResponse doBooking(TransactionRequesterData transactionRequesterData) {
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
                                serverResponse = validateTransactionRequest();
                                if (serverResponse.isSuccess()) {
                                    serverResponse = processBooking();
                                }
                            }
                        }
                    }
                }
            }
        }
        return serverResponse;
    }

    private ServerResponse validateTransactionRequest() {
        ServerResponse serverResponse = new ServerResponse();

        bookingRequests = bookingRequestRepository.findAllByMobileNumberAndCampaign(transactionRequesterData.getMobileNumber(), campaign.getId());

        if (!bookingRequests.isEmpty()) {
            List<BookingRequest> paidRequest = bookingRequests.stream()
                    .filter(request -> request.getStatus().equalsIgnoreCase(Constant.BookingStatus.PAID)).collect(Collectors.toList());
            if (paidRequest.size() >= campaign.getUsagePerCostumer()) {
                throw new IllegalStateException(ResponseMsg.failureResponse(MsgConstant.Customer.ALLOWED_NUMBER_CROSSED));
            }
        }

        bookingLogOptional = bookingLogRepository.findByCampaign(campaign.getId());
        if (bookingLogOptional.isPresent()) {
            BookingLog bookingLog = bookingLogOptional.get();
            if (bookingLog.getTotalUsed() >= campaign.getTotalIssued()) {
                throw new IllegalStateException(ResponseMsg.failureResponse(MsgConstant.Customer.NO_CODE_AVAILABLE));

            }
            if (bookingLog.getTotalBooked() >= campaign.getTotalIssued()) {
                throw new IllegalStateException(ResponseMsg.failureResponse(MsgConstant.Customer.NO_CODE_AVAILABLE));
            }
        }
        serverResponse.setSuccess(true);
        return serverResponse;
    }

    private ServerResponse processBooking() {

        ServerResponse serverResponse = new ServerResponse();

        if (bookingRequests != null && !bookingRequests.isEmpty()) {

            Date currentDate = new Date();
            Optional<BookingRequest> bookingRequestOptional = bookingRequests.stream()
                    .filter(request -> request.getServiceCode().equalsIgnoreCase(this.transactionRequesterData.getServiceCode()))
                    .filter(request -> request.getStatus().equalsIgnoreCase(Constant.BookingStatus.BOOKED))
                    .filter(request -> currentDate.compareTo(request.getExpiryDate()) < 0)
                    .findAny();

            if (bookingRequestOptional.isPresent()) {
                BookingRequest bookingRequest = bookingRequestOptional.get();

                bookingRequest.setExpiryDate(DateHelper.addMinutes(new Date(), bookingRequest.getCampaign().getBookingExpiryTime()));
                bookingRequest.setTxnAmount(transactionRequesterData.getTransactionAmount());

                bookingRequestRepository.save(bookingRequest);

                OfferBookResponse offerBookResponse = BookingMapper.convertToOfferBookResponse(bookingRequest, transactionRequesterData.getTransactionAmount());

                serverResponse.setSuccess(true);
                serverResponse.setMessage("Promo code applied successfully");
                serverResponse.setObj(offerBookResponse);

                return serverResponse;
            }
        }

        Channel channel = channelRepository.getChannelByCode(transactionRequesterData.getChannel()).get();

        BookingRequest bookingRequest = BookingMapper.convertToBookingRequest(transactionRequesterData, channel);
        bookingRequestRepository.save(bookingRequest);

        OfferBookResponse offerBookResponse = BookingMapper.convertToOfferBookResponse(bookingRequest, transactionRequesterData.getTransactionAmount());

        if (bookingLogOptional.isPresent()) {
            BookingLog bookingLog = bookingLogOptional.get();
            int booked = bookingLog.getTotalBooked();
            bookingLog.setTotalBooked(booked + 1);
            bookingLogRepository.save(bookingLog);
        } else {
            BookingLog bookingLog = new BookingLog();
            bookingLog.setCampaign(transactionRequesterData.getCampaign());
            bookingLog.setTotalBooked(1);
            bookingLog.setTotalUsed(0);

            bookingLogRepository.save(bookingLog);
        }

        serverResponse.setSuccess(true);
        serverResponse.setMessage("Promo code applied successfully");
        serverResponse.setObj(offerBookResponse);

        return serverResponse;
    }

}
