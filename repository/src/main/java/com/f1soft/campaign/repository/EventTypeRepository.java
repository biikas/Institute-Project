package com.f1soft.campaign.repository;

import com.f1soft.campaign.entities.model.EventType;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EventTypeRepository extends BaseRepository<EventType> {

    @Query("select t from EventType t order by t.id desc")
    List<EventType> getAllEventType();
}
