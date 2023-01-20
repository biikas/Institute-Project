package com.nikosera.repository.repository.core;

import com.nikosera.entity.UserGroupPermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserGroupPermissionRepository extends JpaRepository<UserGroupPermission, Long> {

    @Query(value = "SELECT * FROM USER_GROUP_PERMISSION WHERE USER_GROUP_ID = ?1", nativeQuery = true)
    List<UserGroupPermission> findAllByUserGroupAndPermission(Long userGroupId);
}

