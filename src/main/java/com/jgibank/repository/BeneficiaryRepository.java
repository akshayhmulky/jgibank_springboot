package com.jgibank.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.jgibank.entity.Beneficiary;

public interface BeneficiaryRepository extends JpaRepository
<Beneficiary, Long>{
}
