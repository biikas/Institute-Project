package com.nikosera.repository.repository.core;

import com.nikosera.entity.ApplicationUser;
import com.nikosera.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author Narayan Joshi
 * <narenzoshi@gmail.com>
 */
@Repository
public interface ApplicationUserRepository extends JpaRepository<ApplicationUser, Long>, QuerydslPredicateExecutor<ApplicationUser> {

    Optional<ApplicationUser> findByUsername(String username);

    Optional<ApplicationUser> findBySecret(String secret);

    @Query(value = "SELECT * FROM `APPLICATION_USER` WHERE USER_GROUP_ID IN (SELECT id FROM `USER_GROUP` WHERE PARENT_ID IN :parentIds)", nativeQuery = true)
    List<ApplicationUser> findByUserGroupByParent(List<String> parentIds);

    Optional<ApplicationUser> findByEmail(String email);

    Boolean existsByEmail(String email);
}
