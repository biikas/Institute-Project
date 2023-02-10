package com.f1soft.campaign.repository;

import com.f1soft.campaign.entities.model.CampaignOffer;
import com.f1soft.campaign.entities.model.OfferMode;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

/**
 * @author Prajwol hada
 */
public interface OfferModeRepository extends BaseRepository<OfferMode> {

    @Query("select t from OfferMode t where t.code = :code ")
    Optional<OfferMode> getByCode(@Param("code") String code);

}
