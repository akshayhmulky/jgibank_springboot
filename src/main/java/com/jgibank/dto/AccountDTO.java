package com.jgibank.dto;

import java.math.BigDecimal;

import com.jgibank.entity.enums.AccountType;
import com.jgibank.entity.enums.BankBranch;
import com.jgibank.entity.enums.IFSCCode;

public class AccountDTO {

    private Long accountId;
    private String accountNumber;
    private BankBranch bankBranch;
    private IFSCCode ifscCode;
    private BigDecimal totalBalance;
    private AccountType accountType;
    private Long customerId; 
    
    // Constructors, getters, and setters

    public AccountDTO() {}

    public AccountDTO(Long accountId, String accountNumber, BankBranch bankBranch, IFSCCode ifscCode, BigDecimal totalBalance, AccountType accountType, Long customerId) {
        this.accountId = accountId;
        this.accountNumber = accountNumber;
        this.bankBranch = bankBranch;
        this.ifscCode = ifscCode;
        this.totalBalance = totalBalance;
        this.accountType = accountType;
        this.customerId = customerId;
    }

    // Getters and Setters
    
    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public BankBranch getBankBranch() {
        return bankBranch;
    }

    public void setBankBranch(BankBranch bankBranch) {
        this.bankBranch = bankBranch;
    }

    public IFSCCode getIfscCode() {
        return ifscCode;
    }

    public void setIfscCode(IFSCCode ifscCode) {
        this.ifscCode = ifscCode;
    }

    public BigDecimal getTotalBalance() {
        return totalBalance;
    }

    public void setTotalBalance(BigDecimal totalBalance) {
        this.totalBalance = totalBalance;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }
}