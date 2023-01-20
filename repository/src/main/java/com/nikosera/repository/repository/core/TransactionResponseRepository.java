package com.nikosera.repository.repository.core;

import com.nikosera.entity.TransactionResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author narayanjoshi
 * @email <narayan.joshi>
 * Created Date: 12/24/22
 */
@Repository
public interface TransactionResponseRepository extends JpaRepository<TransactionResponse, Long> {
}
