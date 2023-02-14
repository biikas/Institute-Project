package com.f1soft.campaign.repository;

import com.f1soft.campaign.entities.model.TutionGroup;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface TutionGroupRepository extends BaseRepository<TutionGroup>{

    @Query("select t from TutionGroup t where t.id =:id and t.active = 'Y'")
    Optional<TutionGroup> findTutionGroupById(Long id);
}
