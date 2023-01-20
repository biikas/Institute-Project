package com.nikosera.repository.repository.core;

import com.nikosera.entity.TransactionRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * @author narayanjoshi
 * @email <narayan.joshi>
 * Created Date: 12/21/22
 */

@Repository
@Transactional
public interface TransactionRequestRepository extends JpaRepository<TransactionRequest, Long> {
}
