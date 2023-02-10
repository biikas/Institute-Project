package com.f1soft.campaign.repository;

import com.f1soft.campaign.entities.model.CampaignEligibleService;
import com.f1soft.campaign.entities.model.Channel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

/**
 * @author Prajwol hada
 */
public interface CampaignEligibleServiceRepository extends BaseRepository<CampaignEligibleService> {

    @Query("select t from CampaignEligibleService t where t.active = 'Y' and t.campaign.id = :campaignId")
    List<CampaignEligibleService> getCampaignEligibleServiceByCampaign(@Param("campaignId") Long campaignId);

}
