package com.f1soft.campaign.repository;

import com.f1soft.campaign.entities.model.CustomLogTracker;
import org.springframework.data.jpa.repository.Query;

/**
 * @author Shreetika Panta
 */
public interface CustomLogTrackerRepository extends BaseRepository<CustomLogTracker> {

    @Query("select max(t.trackId) from CustomLogTracker t where t.campaignId =:campaignId")
    Long getTrackerId(Long campaignId);
}
