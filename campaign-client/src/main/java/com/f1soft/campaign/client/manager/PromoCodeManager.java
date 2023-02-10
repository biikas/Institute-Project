package com.f1soft.campaign.client.manager;

import com.f1soft.campaign.repository.BookingLogRepository;
import com.f1soft.campaign.repository.BookingRequestRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Prajwol hada
 */
@Slf4j
@Component
public class PromoCodeManager {

    @Autowired
    private BookingRequestRepository bookingRequestRepository;

    @Autowired
    private BookingLogRepository bookingLogRepository;

//    public ServerResponse bookOffer(TransactionRequesterData transactionRequesterData) {

//        ServerResponse serverResponse = new ServerResponse();
//
//        Optional<BookingRequest> bookingRequestOptional = bookingRequestRepository.findByMobileNumberAndServiceCode(transactionRequesterData.getMobileNumber(), transactionRequesterData.getServiceCode(), transactionRequesterData.getCampaign().getId());
//        if (bookingRequestOptional.isPresent()) {
//            BookingRequest bookingRequest = bookingRequestOptional.get();
//            Date expiryDate = bookingRequest.getExpiryDate();
//            Date currentDate = new Date();
//            if (currentDate.compareTo(expiryDate) < 0) {
//                OfferBookResponse offerBookResponse = BookingMapper.convertToOfferBookResponse(bookingRequest);
//
//                serverResponse.setSuccess(true);
//                serverResponse.setMessage("Promo code applied successfully");
//                serverResponse.setObj(offerBookResponse);
//
//                return serverResponse;
//            }
//        }
//
//        BookingRequest bookingRequest = BookingMapper.convertToBookingRequest(transactionRequesterData);
//        bookingRequestRepository.save(bookingRequest);
//
//        OfferBookResponse offerBookResponse = BookingMapper.convertToOfferBookResponse(bookingRequest);
//
//        Optional<BookingLog> bookingLogOptional = bookingLogRepository.findByCampaign(bookingRequest.getCampaign().getId());
//        if (bookingLogOptional.isPresent()) {
//            BookingLog bookingLog = bookingLogOptional.get();
//            int booked = bookingLog.getTotalBooked();
//            bookingLog.setTotalBooked(booked + 1);
//            bookingLogRepository.save(bookingLog);
//        } else {
//            BookingLog bookingLog = new BookingLog();
//            bookingLog.setCampaign(transactionRequesterData.getCampaign());
//            bookingLog.setTotalBooked(1);
//            bookingLog.setTotalUsed(0);
//
//            bookingLogRepository.save(bookingLog);
//        }
//
//        serverResponse.setSuccess(true);
//        serverResponse.setMessage("Promo code applied successfully");
//        serverResponse.setObj(offerBookResponse);
//
//        return serverResponse;
//    }
}
