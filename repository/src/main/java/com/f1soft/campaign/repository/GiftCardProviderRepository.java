package com.f1soft.campaign.repository;

import com.f1soft.campaign.entities.model.GiftCardProvider;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

/**
 * @author <krishna.pandey@f1soft.com>
 */
public interface GiftCardProviderRepository extends BaseRepository<GiftCardProvider> {

    @Query("select t from GiftCardProvider t where t.active = 'Y' ")
    List<GiftCardProvider> findAllActive();

    @Query("select t from GiftCardProvider t where t.active = 'Y' AND t.code=:code")
    Optional<GiftCardProvider> getGiftCardDetailByCode(@Param("code") String code);
}