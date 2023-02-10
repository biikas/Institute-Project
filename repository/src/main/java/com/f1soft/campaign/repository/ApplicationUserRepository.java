package com.f1soft.campaign.repository;

import com.f1soft.campaign.entities.model.ApplicationUser;
import com.f1soft.campaign.repository.custom.ApplicationUserCustom;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ApplicationUserRepository extends BaseRepository<ApplicationUser>, ApplicationUserCustom {

    @Query("select t from ApplicationUser t where t.active ='Y' and t.username =:username")
    Optional<ApplicationUser> findApplicationUserByUsername(String username);

    @Query("select t from ApplicationUser t where t.active ='Y' and t.id =:id")
    Optional<ApplicationUser> findApplicationUserById(Long id);

    @Query("Select t from ApplicationUser t where t.username = :username")
    List<ApplicationUser> checkUserNameExist(String username);

    @Query("select t from ApplicationUser t where t.active = 'Y'")
    List<ApplicationUser> getAllUser();
}
