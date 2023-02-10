package com.f1soft.campaign.repository;

import com.f1soft.campaign.entities.model.CampaignTotalRedeem;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CampaignTotalRedeemRepository extends BaseRepository<CampaignTotalRedeem> {

    @Query("select t from CampaignTotalRedeem t where t.campaign.id =:campaignId")
    Optional<CampaignTotalRedeem> getCampaignTotalRedeemByCampaignId(Long campaignId);

}
