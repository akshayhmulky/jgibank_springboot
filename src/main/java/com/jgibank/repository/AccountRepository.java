package com.jgibank.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jgibank.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Long>{
	Optional<Account> findByAccountNumber(String accountNumber);
}
