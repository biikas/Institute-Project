package com.f1soft.campaign.repository;

import com.f1soft.campaign.entities.model.SystemConfig;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface SystemConfigRepository extends BaseRepository<SystemConfig> {

    @Query("select t from SystemConfig t where t.id =:id and t.active = 'Y'")
    Optional<SystemConfig> findSystemConfigById(Integer id);

    @Query("select t from SystemConfig t where t.paramCode =:paramCode")
    Optional<SystemConfig> findSystemConfigByParamCode(String paramCode);
}
