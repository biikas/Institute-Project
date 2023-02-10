package com.f1soft.campaign.repository;

import com.f1soft.campaign.entities.model.BookingRequest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

/**
 * @author Prajwol hada
 */
public interface BookingRequestRepository extends BaseRepository<BookingRequest> {

    @Query("select t from BookingRequest t where t.campaign.id = :campaignId AND t.mobileNumber = :mobileNumber AND t.serviceCode = :serviceCode")
    List<BookingRequest> findAllByMobileNumberAndServiceCode(@Param("mobileNumber") String mobileNumber, @Param("serviceCode") String serviceCode, @Param("campaignId") Long campaignId);

    @Query("select t from BookingRequest t where t.bookingId = :bookingId")
    Optional<BookingRequest> findByBookingId(@Param("bookingId") String bookingId);

    @Query("select t from BookingRequest t where t.campaign.id = :campaignId AND t.mobileNumber = :mobileNumber")
    List<BookingRequest> findAllByMobileNumberAndCampaign(String mobileNumber, Long campaignId);
}
