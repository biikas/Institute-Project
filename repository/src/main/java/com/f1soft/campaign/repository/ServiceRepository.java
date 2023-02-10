package com.f1soft.campaign.repository;

import com.f1soft.campaign.entities.model.Channel;
import com.f1soft.campaign.entities.model.Service;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

/**
 * @author Prajwol hada
 */
public interface ServiceRepository extends BaseRepository<Service> {

    @Query("select t from Service t where t.code = :code")
    Optional<Service> getServiceByCode(@Param("code") String code);
}
