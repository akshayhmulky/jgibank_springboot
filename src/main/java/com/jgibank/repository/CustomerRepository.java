package com.jgibank.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jgibank.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>{
	  Optional<Customer> findByUsername(String email);
	  Customer findFirstByUsername(String email);
}
