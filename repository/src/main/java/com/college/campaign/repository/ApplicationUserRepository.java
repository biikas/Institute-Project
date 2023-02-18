package com.college.campaign.repository;

import com.college.campaign.entities.model.ApplicationUser;
import com.college.campaign.repository.custom.ApplicationUserCustom;
import com.querydsl.core.BooleanBuilder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ApplicationUserRepository extends JpaRepository<ApplicationUser,Long>, ApplicationUserCustom {

    @Query("select t from ApplicationUser t where t.active ='Y' and t.username =:username")
    Optional<ApplicationUser> findApplicationUserByUsername(String username);

    @Query("select t from ApplicationUser t where t.active ='Y' and t.id =:id")
    Optional<ApplicationUser> findApplicationUserById(Long id);

    @Query("Select t from ApplicationUser t where t.username = :username")
    List<ApplicationUser> checkUserNameExist(String username);

    @Query("select t from ApplicationUser t where t.active = 'Y'")
    List<ApplicationUser> getAllUser();

    @Query("select t from ApplicationUser t where t.active = 'Y'")
    List<ApplicationUser> findAll();

    Page<ApplicationUser> findAll(BooleanBuilder searchQuery, PageRequest id);
}
