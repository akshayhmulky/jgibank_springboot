package com.jgibank.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jgibank.entity.Customer;
import com.jgibank.entity.Account;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>{
	  Optional<Customer> findByUsername(String username);
      
}
