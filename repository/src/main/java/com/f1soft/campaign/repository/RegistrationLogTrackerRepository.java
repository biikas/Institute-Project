package com.f1soft.campaign.repository;

import com.f1soft.campaign.entities.model.RegistrationLogTracker;
import org.springframework.data.jpa.repository.Query;

/**
 * @author Shreetika Panta
 */
public interface RegistrationLogTrackerRepository extends BaseRepository<RegistrationLogTracker> {

    @Query("select max(r.trackId) from RegistrationLogTracker r")
    Long getTrackerId();
}
