package com.college.campaign.repository;

import com.college.campaign.entities.model.SystemConfigMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface SystemConfigMasterRepository extends JpaRepository<SystemConfigMaster,Long> {
    @Query("select t FROM SystemConfigMaster t WHERE t.code = :code and t.active = 'Y'")
    SystemConfigMaster getSystemConfigByCode(String code);

    @Query("select t from SystemConfigMaster t where t.id =:id and t.active = 'Y'")
    Optional<SystemConfigMaster> findSystemConfigMasterById(Integer id);

    @Query("select t from SystemConfigMaster t where t.code =:code")
    Optional<SystemConfigMaster> findSystemConfigMasterByCode(String code);
}
