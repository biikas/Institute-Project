package com.f1soft.campaign.repository;

import com.f1soft.campaign.entities.model.IsoTxnRequest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IsoTxnRequestRepository extends BaseRepository<IsoTxnRequest> {

    @Query("select t from IsoTxnRequest t where t.requestInfo.id=:requestInfoId")
    Optional<IsoTxnRequest> findIsoTxnRequestByRequestInfoId(Long requestInfoId);
}
