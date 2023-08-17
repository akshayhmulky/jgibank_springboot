package com.jgibank.service;

import java.math.BigDecimal;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jgibank.entity.Account;
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
}
