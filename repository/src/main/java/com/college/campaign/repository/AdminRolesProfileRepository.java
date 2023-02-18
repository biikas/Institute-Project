package com.college.campaign.repository;

import com.college.campaign.entities.model.AdminRolesProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @Author Nitesh Poudel
 */
public interface AdminRolesProfileRepository extends JpaRepository<AdminRolesProfile,Long> {

    @Query("SELECT t FROM AdminRolesProfile t where t.enabled = 'Y' and t.adminType.id = :adminTypeId")
    List<AdminRolesProfile> loadAllActiveRolesByAdminType(@Param("adminTypeId") Long adminTypeId);
}
