package com.college.campaign.repository;

import com.college.campaign.entities.model.AdminPasswordChangeLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AdminPasswordChangeLogRepository extends JpaRepository<AdminPasswordChangeLog,Long> {

    @Query("select t from AdminPasswordChangeLog t where t.userId.id = :userId order by t.id desc")
    Page<AdminPasswordChangeLog> recentPasswordChangeLogs(Long userId, Pageable pageRequest);
}
