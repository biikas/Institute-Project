package com.f1soft.campaign.repository;

import com.f1soft.campaign.entities.model.CustomCBSConnection;
import com.f1soft.campaign.repository.custom.CustomCBSConnectionCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.Optional;

/**
 * @author Shreetika Panta
 */
public interface CustomCbsConnectionRepository extends QuerydslPredicateExecutor<CustomCBSConnection>, JpaRepository<CustomCBSConnection, Long>, CustomCBSConnectionCustom {

    @Query("select c from CustomCBSConnection c where c.id=:id")
    CustomCBSConnection getCBSConnectionById(Long id) ;

    @Query("select c from CustomCBSConnection c where c.cbsConnectionURL =:cbsConnectionURL")
    Optional<CustomCBSConnection> findByConnectionUrl(String cbsConnectionURL);
}
