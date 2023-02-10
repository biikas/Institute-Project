package com.f1soft.campaign.repository;

import com.f1soft.campaign.entities.model.EventAttribute;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

/**
 * @author Bikash Shah
 */
public interface EventAttributeRepository extends BaseRepository<EventAttribute>{

    @Query("select t from EventAttribute t where t.campaign.id = :id ")
    Optional<EventAttribute> getByCampaignId(Long id);
}
