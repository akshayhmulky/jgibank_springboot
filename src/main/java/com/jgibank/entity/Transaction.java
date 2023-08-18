package com.jgibank.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name="transaction")
public class Transaction {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="transaction_id", nullable=false)
	private Long transactionId;
	
    @Column(name="created_at", nullable=false, updatable=false)
    @CreationTimestamp
    private Timestamp transactionDate; 
    
    @NotNull(message="Transfer from should not be null")
    @NotBlank(message="Transfer from should not be blank")
    @Column(name="transfer_from", nullable=false)
	private String transferFrom;
    
    @NotNull(message="Transfer to should not be null")
    @NotBlank(message="Transfer to should not be blank")
    @Column(name="transfer_to", nullable=false)
	private String transferTo;
	
    @NotNull(message = "Withdraw amount must not be null")
    @Column(name="withdraw")
	private BigDecimal withdraw;
    
    @NotNull(message = "Deposit amount must not be null")
    @Column(name="deposit")
	private BigDecimal deposit;
    
    
    @NotNull(message = "Balance amount must not be null")
    @Column(name="balance")
	private BigDecimal balance;
    
    @Column(name = "account_id", nullable = false)
    @NotNull(message="AccountId cannot be null")
    private Long accountId;
    
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id" , referencedColumnName = "account_id", insertable = false, updatable = false)
    private Account account;

    //Constructors
    
    public Transaction() {
    }

    public Transaction(
            String transferFrom,
            String transferTo,
            BigDecimal withdraw,
            BigDecimal deposit,
            BigDecimal balance,
            Long accountId
    ) {
        this.transferFrom = transferFrom;
        this.transferTo = transferTo;
        this.withdraw = withdraw;
        this.deposit = deposit;
        this.balance = balance;
        this.accountId = accountId;
    }
    
    
    //Getters and Setters
    public Timestamp getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Timestamp transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getTransferFrom() {
        return transferFrom;
    }

    public void setTransferFrom(String transferFrom) {
        this.transferFrom = transferFrom;
    }

    public String getTransferTo() {
        return transferTo;
    }

    public void setTransferTo(String transferTo) {
        this.transferTo = transferTo;
    }

    public BigDecimal getWithdraw() {
        return withdraw;
    }

    public void setWithdraw(BigDecimal withdraw) {
        this.withdraw = withdraw;
    }

    public BigDecimal getDeposit() {
        return deposit;
    }

    public void setDeposit(BigDecimal deposit) {
        this.deposit = deposit;
    }

    
	public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public Long getTransactionId() {
		return transactionId;
	}

 
    
}
