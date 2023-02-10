package com.f1soft.campaign.repository;

import com.f1soft.campaign.entities.model.AdminPasswordChangeLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

public interface AdminPasswordChangeLogRepository extends BaseRepository<AdminPasswordChangeLog> {

    @Query("select t from AdminPasswordChangeLog t where t.userId.id = :userId order by t.id desc")
    Page<AdminPasswordChangeLog> recentPasswordChangeLogs(Long userId, Pageable pageRequest);
}
