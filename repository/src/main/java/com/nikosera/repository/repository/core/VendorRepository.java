package com.nikosera.repository.repository.core;

import com.nikosera.entity.Vendor;
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
public interface VendorRepository extends JpaRepository<Vendor, Long> {

    @Query(value = "SELECT V from VENDOR V WHERE V.code=:code AND V.active = 'Y'")
    Vendor getAllByActive(@Param("code") String code);


}
