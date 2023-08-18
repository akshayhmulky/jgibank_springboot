package com.jgibank.service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jgibank.repository.AccountRepository;
import com.jgibank.repository.BeneficiaryRepository;
import com.jgibank.dto.AddBeneficiaryRequestDTO;
import com.jgibank.entity.Account;
import com.jgibank.entity.Beneficiary;

@Service
public class BeneficiaryService {

	@Autowired
	private BeneficiaryRepository beneficiaryRepository;
	
	@Autowired
	private AccountRepository accountRepostiory;
	
	public Beneficiary addBeneficiary(AddBeneficiaryRequestDTO beneficiary) {
        Account account = accountRepostiory.findByAccountNumber(beneficiary.getAccountNumber()).orElse(null);
        Account beneficiaryAccount = accountRepostiory.findByAccountNumber(beneficiary.getBeneficiaryAccountNumber()).orElse(null);
        //If beneficiary account is invalid then throw an exception
        if(beneficiaryAccount==null) {
        	throw new IllegalArgumentException("Invalid Beneficiary Account number");
        }
        //If beneficiary account and self account is same, then throw an exception
        if(account!=null && (account.getAccountNumber().equals(beneficiary.getBeneficiaryAccountNumber()))) {
        	throw new IllegalArgumentException("Beneficiary Account Number and Self Account cannot be same");
        }
        
        Beneficiary ben = convertToBeneificiaryEntity(beneficiary, account.getAccountId());
		
	    return beneficiaryRepository.save(ben);

		

	}
	
	private Beneficiary convertToBeneificiaryEntity(AddBeneficiaryRequestDTO beneficiary, Long accountId) {
		Beneficiary ben = new Beneficiary();
		ben.setBeneficiaryName(beneficiary.getBeneficiaryName());
		ben.setBeneficiaryAccountNumber(beneficiary.getBeneficiaryAccountNumber());
		
        //Setting default bank name, since we are allowing only internal fund transfer for now
		ben.setBeneficiaryBankName("JGIBank");
		ben.setAccountId(accountId);
		ben.setMaximumTransferLimit(beneficiary.getMaximumTransferLimit());
		return ben;
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
