package com.f1soft.campaign.repository;

import com.f1soft.campaign.entities.model.Campaign;
import com.f1soft.campaign.repository.custom.CampaignCustom;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

/**
 * @author Prajwol hada
 */
public interface CampaignRepository extends BaseRepository<Campaign>, CampaignCustom {

    @Query("select t from Campaign t order by t.id desc")
    List<Campaign> getAllCampaignList();

    @Query("select t from Campaign t where t.active = 'Y' order by t.id desc")
    List<Campaign> getAllActiveCampaignList();

    @Query("select t from Campaign t where t.active = 'Y' AND t.promoCode=:promoCode")
    Optional<Campaign> getCampaignByPromoCode(@Param("promoCode") String promoCode);

    @Query("select t from Campaign t where t.active = 'Y' and t.status = 'ACTIVE' and t.eventTypeId.type =:eventType")
    List<Campaign> getCampaignByEventType(String eventType);

}
