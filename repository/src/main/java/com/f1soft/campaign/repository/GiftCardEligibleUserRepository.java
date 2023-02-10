package com.f1soft.campaign.repository;

import com.f1soft.campaign.entities.model.GiftCardEligibleUser;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GiftCardEligibleUserRepository extends BaseRepository<GiftCardEligibleUser> {

    @Query("select t from GiftCardEligibleUser t where t.giftCard.id =:giftCardId")
    List<GiftCardEligibleUser> getByGiftCardId(@Param("giftCardId")Long giftCardId);
}
