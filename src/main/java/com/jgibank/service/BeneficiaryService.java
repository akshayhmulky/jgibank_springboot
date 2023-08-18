package com.jgibank.service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jgibank.repository.AccountRepository;
import com.jgibank.repository.BeneficiaryRepository;
import com.jgibank.entity.Account;
import com.jgibank.entity.Beneficiary;

@Service
public class BeneficiaryService {

	@Autowired
	private BeneficiaryRepository beneficiaryRepository;
	
	@Autowired
	private AccountRepository accountRepostiory;
	
	public Beneficiary addBeneficiary(Beneficiary beneficiary) {
        Account account = accountRepostiory.findById(beneficiary.getAccountId()).orElse(null);
        Account beneficiaryAccount = accountRepostiory.findByAccountNumber(beneficiary.getBeneficiaryAccountNumber()).orElse(null);
        //If beneficiary account is invalid then throw an exception
        if(beneficiaryAccount==null) {
        	throw new IllegalArgumentException("Invalid Beneficiary Account number");
        }
        //If beneficiary account and self account is same, then throw an exception
        if(account!=null && (account.getAccountNumber().equals(beneficiary.getBeneficiaryAccountNumber()))) {
        	throw new IllegalArgumentException("Beneficiary Account Number and Self Account cannot be same");
        }
        
        //Setting default bank name, since we are allowing only internal fund transfer for now
		beneficiary.setBeneficiaryBankName("JGIBank");
		
	    return beneficiaryRepository.save(beneficiary);

	}
	
	
	//Get AccountNumber from beneficiaryId
	public String getAccountNumberFromBeneficiaryId(Long beneficiaryId) {
		Beneficiary beneficiary = beneficiaryRepository.findById(beneficiaryId).orElse(null);
		String beneficiaryAccountNumber = beneficiary.getBeneficiaryAccountNumber();
		return beneficiaryAccountNumber;
	}
	
	
	public BigDecimal getMaximumTransferLimit(Long beneficiaryId) {
		Beneficiary beneficiary = beneficiaryRepository.findById(beneficiaryId).orElse(null);
		return beneficiary.getMaximumTransferLimit();
	}
}
