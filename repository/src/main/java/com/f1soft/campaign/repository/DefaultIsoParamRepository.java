package com.f1soft.campaign.repository;

import com.f1soft.campaign.entities.model.DefaultIsoParam;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;

import javax.persistence.QueryHint;
import java.util.List;

public interface DefaultIsoParamRepository extends BaseRepository<DefaultIsoParam> {

    @Query("select t from DefaultIsoParam t WHERE t.active = 'Y' and t.type = :type")
    List<DefaultIsoParam> getDefaultIsoParamByType(String type);
}
