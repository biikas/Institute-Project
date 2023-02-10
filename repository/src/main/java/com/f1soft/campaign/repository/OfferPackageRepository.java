package com.f1soft.campaign.repository;

import com.f1soft.campaign.entities.model.OfferPackage;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

/**
 * @author Prajwol hada
 */
public interface OfferPackageRepository extends BaseRepository<OfferPackage> {

    @Query("select t from OfferPackage t where t.offerMode.id =:offerModeId")
    List<OfferPackage> findByOfferModeId(Long offerModeId);

    @Query("select t from OfferPackage t where t.offerMode.id =:offerModeId and t.code=:code")
    Optional<OfferPackage> findByOfferModeAndCode(Long offerModeId, String code);
}
