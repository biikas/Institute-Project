package com.f1soft.campaign.repository;

import com.f1soft.campaign.entities.model.OfferProduct;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OfferProductRepository extends BaseRepository<OfferProduct> {

    @Query("select t from OfferProduct t WHERE  t.offerMode.id = :id")
    List<OfferProduct> getAllOfferProductByOfferModeId(Long id);
}
