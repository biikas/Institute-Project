package com.f1soft.campaign.repository;

import com.f1soft.campaign.entities.model.HmacClient;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface HmacClientRepository extends BaseRepository<HmacClient> {
    @Query("select t from HmacClient t where t.active = 'Y'")
    List<HmacClient> findAllByActive();

    @Query("select t from HmacClient t where t.apiKey =:apiKey and t.active = 'Y'")
    HmacClient findByActiveApiKey(String apiKey);
}
