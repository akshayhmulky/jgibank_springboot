package com.jgibank.service;

import java.math.BigDecimal;
import java.util.UUID;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jgibank.dto.CustomerResponseDTO;
import com.jgibank.entity.Account;
import com.jgibank.entity.Customer;
import com.jgibank.repository.AccountRepository;
import com.jgibank.utility.GenerateBankAccountNumber;

@Service
public class AccountService {

  @Autowired
  private AccountRepository accountRepository;
  
  
  
  //Account Creation by Bank Admin
  public Account addAccount(Account account) {
		 String accountNumber = GenerateBankAccountNumber.generateBankAccountNumber();
		 account.setAccountNumber(accountNumber);
		 account.setTotalBalance(new BigDecimal(0));
		 return accountRepository.save(account);
  }
  
  //Get Account by Account Number
  public Account getAccountByAccountNumber(String accountNumber) {
	  return accountRepository.findByAccountNumber(accountNumber).orElse(null);
  }
  
  //Get AccountNumber by AccountId
  public String getAccountNumberByAccountId(Long accountId) {
	  Account account = accountRepository.findById(accountId).orElse(null);
	  String accountNumber = account.getAccountNumber();
	  return accountNumber;
  }
  
  //Get AccountId by AccountNumber
  public Long getAccountIdByAccountNumber(String accountNumber) {
	  Account account = accountRepository.findByAccountNumber(accountNumber).orElse(null);
	  return account.getAccountId();
  }
  
  
  //Deduct Balance
  public BigDecimal deductBalance(String accountNumber, BigDecimal amount) {
	  Account account = accountRepository.findByAccountNumber(accountNumber).orElse(null);
	  
	  BigDecimal totalBalance = account.getTotalBalance();
	  int compareAmount = totalBalance.compareTo(amount);
	  if(compareAmount>0) {
      // Subtract the amount from the balance
      totalBalance = totalBalance.subtract(amount);
	  account.setTotalBalance(totalBalance);
	  accountRepository.save(account);
	  return totalBalance;
	  }else {
		  throw new IllegalArgumentException("Low balance, hence cannot transfer fund!");
	  }
  }
  
  //Add Balance
  public BigDecimal addBalance(String accountNumber, BigDecimal amount) {
	  Account account = accountRepository.findByAccountNumber(accountNumber).orElse(null);
	  
	  BigDecimal totalBalance = account.getTotalBalance();
      // Subtract the amount from the balance
      totalBalance = totalBalance.add(amount);
	  account.setTotalBalance(totalBalance);
	  accountRepository.save(account);
	  return totalBalance;

  }
}
