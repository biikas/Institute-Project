package com.f1soft.campaign.repository;

import com.f1soft.campaign.entities.model.CustomCampaignEligibleUser;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CustomCampaignEligibleUserRepository extends BaseRepository<CustomCampaignEligibleUser> {

    @Query("select t from CustomCampaignEligibleUser t where t.username =:username and t.campaign.id =:campaignId")
    Optional<CustomCampaignEligibleUser> findByUsernameAndCampaignId(String username, Long campaignId);


}
