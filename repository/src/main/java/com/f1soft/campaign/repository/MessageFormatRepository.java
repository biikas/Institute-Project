package com.f1soft.campaign.repository;

import com.f1soft.campaign.entities.model.MessageFormat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface MessageFormatRepository extends JpaRepository<MessageFormat, Long> {

    @Query("select t from MessageFormat t")
    List<MessageFormat> messageFormat();

    @Query("select t from MessageFormat t order by t.message asc")
    List<MessageFormat> findAllMessgeFormats();
}
