package com.f1soft.campaign.repository;

import com.f1soft.campaign.entities.model.CampaignEligibleUser;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CampaignEligibleUserRepository extends BaseRepository<CampaignEligibleUser> {

    @Query("select t from CampaignEligibleUser t where t.campaign.id =:campaignId")
    List<CampaignEligibleUser> getCampaignEligibleUserByCampaignId(@Param("campaignId")Long campaignId);

    @Query("select t from CampaignEligibleUser t where t.userName =:userName and t.promoCode =:promoCode")
    Optional<CampaignEligibleUser> findCampaignEligibleUserByUserNameAndPromoCodeAndCampaignId(String userName, String promoCode);
}
