package com.nikosera.repository.repository.core;

import com.nikosera.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * @author narayanjoshi
 * @email <narayan.joshi>
 * Created Date: 12/24/22
 */
@Repository
public interface StatusRepository extends JpaRepository<Status, Long> {

    @Query(value = "SELECT S from STATUS S WHERE S.code=:code")
    Status getByCode(@Param("code") String code);
}
