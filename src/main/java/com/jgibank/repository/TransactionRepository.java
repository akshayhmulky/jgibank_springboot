package com.jgibank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

import com.jgibank.entity.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long>{

	List<Transaction> findByAccountId(Long accountId);
}
