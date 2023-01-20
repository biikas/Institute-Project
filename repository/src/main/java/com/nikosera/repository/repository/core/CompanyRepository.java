package com.nikosera.repository.repository.core;

import com.nikosera.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long>, QuerydslPredicateExecutor<Company> {

    @Query("select t from COMPANY t where t.active = 'Y'")
    List<Company> getAllByActive();
}
