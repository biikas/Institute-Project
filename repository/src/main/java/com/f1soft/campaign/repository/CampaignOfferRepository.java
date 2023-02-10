package com.f1soft.campaign.repository;

import com.f1soft.campaign.entities.model.CampaignOffer;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author Prajwol hada
 */
public interface CampaignOfferRepository extends BaseRepository<CampaignOffer> {

    @Query("select t from CampaignOffer  t where t.campaign.id =:campaignId")
    List<CampaignOffer> findByCampaignId(Long campaignId);
}
