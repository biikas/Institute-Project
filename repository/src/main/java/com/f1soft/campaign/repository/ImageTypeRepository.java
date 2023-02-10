package com.f1soft.campaign.repository;

import com.f1soft.campaign.entities.model.ImageType;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

/**
 * @author Prajwol Hada
 */
public interface ImageTypeRepository extends BaseRepository<ImageType> {

    @Query("select t from ImageType t where t.active = 'Y' and t.type = :type")
    Optional<ImageType> findByType(@Param("type") String type);
}
