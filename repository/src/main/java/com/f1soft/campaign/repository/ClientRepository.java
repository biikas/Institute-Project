package com.f1soft.campaign.repository;

import com.f1soft.campaign.entities.model.Client;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ClientRepository extends BaseRepository<Client> {

    @Query("select t from Client t")
    List<Client> getAllClient();

    @Query("select t from Client t where t.username = :username")
    Optional<Client> findByUsername(@Param("username") String username);
}
