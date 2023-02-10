package com.f1soft.campaign.repository;

import com.f1soft.campaign.entities.model.CBSQuery;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Sabrin Luitel
 */
@Repository
public interface CBSQueryRepository extends BaseRepository<CBSQuery> {

    @Query("select t from CBSQuery  t where t.queryCode = :code")
    Optional<CBSQuery> findByCode(@Param("code") String code);

}
