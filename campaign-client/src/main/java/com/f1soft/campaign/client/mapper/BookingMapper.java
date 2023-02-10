package com.f1soft.campaign.client.mapper;

import com.f1soft.campaign.client.constant.Constant;
import com.f1soft.campaign.client.dto.TransactionRequesterData;
import com.f1soft.campaign.client.dto.response.OfferBookResponse;
import com.f1soft.campaign.client.util.BookingHelper;
import com.f1soft.campaign.common.util.DateHelper;
import com.f1soft.campaign.entities.model.BookingRequest;
import com.f1soft.campaign.entities.model.Channel;

import java.util.Date;

/**
 * @author Prajwol hada
 */
public class BookingMapper {

    private BookingMapper() {

    }

    public static OfferBookResponse convertToOfferBookResponse(BookingRequest bookingRequest, Double totalAmount) {
        OfferBookResponse offerBookResponse = new OfferBookResponse();

        offerBookResponse.setOfferBookingId(bookingRequest.getBookingId());
        offerBookResponse.setCashback(BookingHelper.calculateAmount(totalAmount, bookingRequest.getCampaign(), bookingRequest.getMobileNumber()));
        offerBookResponse.setExpiryTime(String.valueOf(bookingRequest.getCampaign().getBookingExpiryTime()));
        offerBookResponse.setPolicy(bookingRequest.getCampaign().getPolicy());

        return offerBookResponse;
    }

    public static BookingRequest convertToBookingRequest(TransactionRequesterData transactionRequesterData, Channel channel) {
        BookingRequest bookingRequest = new BookingRequest();

        bookingRequest.setBookingId(BookingHelper.generateBookingId(transactionRequesterData.getMobileNumber()));
        bookingRequest.setCampaign(transactionRequesterData.getCampaign());
        bookingRequest.setMobileNumber(transactionRequesterData.getMobileNumber());
        bookingRequest.setServiceCode(transactionRequesterData.getServiceCode());
        bookingRequest.setRecordedDate(new Date());
        bookingRequest.setStatus(Constant.BookingStatus.BOOKED);
        bookingRequest.setExpiryDate(DateHelper.addMinutes(new Date(), bookingRequest.getCampaign().getBookingExpiryTime()));
        bookingRequest.setChannel(channel);
        bookingRequest.setProfileId(transactionRequesterData.getProfileId());
        bookingRequest.setTxnAmount(transactionRequesterData.getTransactionAmount());
        bookingRequest.setEmailAddress(transactionRequesterData.getEmailAddress());
        bookingRequest.setCustomerName(transactionRequesterData.getCustomerName());

        return bookingRequest;
    }


}
