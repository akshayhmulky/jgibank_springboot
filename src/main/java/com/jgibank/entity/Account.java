package com.jgibank.entity;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jgibank.entity.enums.AccountType;
import com.jgibank.entity.enums.BankBranch;
import com.jgibank.entity.enums.IFSCCode;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name="account")
public class Account {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="account_id", nullable=false)
	private Long accountId;                    //Auto Generation
	
	@Column(name="account_number", unique=true, nullable=false)
	private String accountNumber;               //Auto Generation
	
	@Column(name="bank_branch", nullable=false)
	@NotNull(message="Bank Branch cannot be null")
	@Enumerated(EnumType.STRING)
	private BankBranch bankBranch;
	
	@Column(name="ifsc_code", nullable=false)
	@NotNull(message="IFSC Code cannot be null")
	@Enumerated(EnumType.STRING)
	private IFSCCode ifscCode;
	
	@Column(name="minimum_balance", nullable=false)
	@NotNull(message="Minimum balance cannot be null")
	private BigDecimal minimumBalance;             //Default value is set in AccountService
	
	@Column(name="total_balance", nullable=false)
	private BigDecimal totalBalance;               //Default value is set in AccountService
	
	@Column(name="account_type", nullable=false)
	@NotNull(message="Account Type cannot be null")
	@Enumerated(EnumType.STRING)
	private AccountType accountType;
	
	
    @Column(name = "customer_id", nullable = false)
    @NotNull(message="Customer Id cannot be null")
    private Long customerId;
	
	//Many Accounts One Customer Relationship
	//It is only for foreign
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", referencedColumnName = "customer_id", insertable = false, updatable = false)
    private Customer customer;
    
    
    //One Account many Beneficiary Relationship
    //Useful in fetching beneficiaries associated with this account 
    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Beneficiary> beneficiaries = new HashSet<>();
    
    //One Account many Transaction Relationship
    //Useful in fetching Transactions associated with this account
    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Transaction> transactions = new HashSet<>();

	public Account() {}

	public Account(Long accountId, String accountNumber, BankBranch bankBranch, IFSCCode ifscCode, BigDecimal minimumBalance, BigDecimal totalBalance, AccountType accountType, Long customerId) {
		super();
		this.accountId = accountId;
		this.accountNumber = accountNumber;
		this.bankBranch = bankBranch;
		this.ifscCode = ifscCode;
		this.minimumBalance=minimumBalance;
		this.totalBalance = totalBalance;
		this.accountType = accountType;
		this.customerId = customerId;	
		}

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
	
	

	public BigDecimal getMinimumBalance() {
		return minimumBalance;
	}

	public void setMinimumBalance(BigDecimal minimumBalance) {
		this.minimumBalance = minimumBalance;
	}

	public BankBranch getBankBranch() {
		return bankBranch;
	}

	public void setBankName(BankBranch bankBranch) {
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

	public Customer getCustomer() {
		return customer;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public Set<Transaction> getTransactions() {
		return transactions;
	}
	
	public Set<Beneficiary> getBeneficiaries() {
		return beneficiaries;
	}
	
    
}
