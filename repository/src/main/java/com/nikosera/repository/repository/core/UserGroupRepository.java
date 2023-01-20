package com.nikosera.repository.repository.core;

import com.nikosera.entity.UserGroup;
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
public interface UserGroupRepository extends JpaRepository<UserGroup, Long> , QuerydslPredicateExecutor<UserGroup> {

    @Query(value = "SELECT * FROM USER_GROUP WHERE PARENT_ID IS NULL", nativeQuery = true)
    List<UserGroup> findParentGroup();

    @Query(value = "SELECT * FROM (SELECT * FROM USER_GROUP ORDER BY PARENT_ID, name ASC ) user_group_sorted, (SELECT @pv \\:=?1) initialisation WHERE FIND_IN_SET(PARENT_ID, @pv) AND LENGTH(@pv \\:=CONCAT(@pv, ',', id))", nativeQuery = true)
    List<UserGroup> findChildGroup(Long id);

    @Query(value = "SELECT * FROM (SELECT * FROM USER_GROUP ORDER BY PARENT_ID, ID) user_group_sorted, (SELECT @pv \\:=:id) initialisation WHERE FIND_IN_SET(PARENT_ID, @pv) AND LENGTH(@pv \\:=CONCAT(@pv, ',', id)) AND NAME LIKE IFNULL(:name, '%') AND IFNULL(:active, ACTIVE) = active", nativeQuery = true)
    List<UserGroup> findChildGroupConditional(Long id, String name, Character active);

    Optional<UserGroup> findUserGroupByName(String name);

    Optional<UserGroup> findUserGroupByNameAndIdNot(String name, Long id);
}
