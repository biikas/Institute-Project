package com.f1soft.campaign.repository;

import com.f1soft.campaign.entities.model.Channel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

/**
 * @author Prajwol hada
 */
public interface ChannelRepository extends BaseRepository<Channel> {

    @Query("select t from Channel t where t.code = :code")
    Optional<Channel> getChannelByCode(@Param("code") String code);
}
