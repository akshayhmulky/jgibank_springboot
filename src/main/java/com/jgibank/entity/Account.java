package com.jgibank.entity;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

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
	private Long accountId;
	
	@NotBlank(message = "Account Number cannot be blank")
	@NotNull(message="Account Number cannot be null")
	@Column(name="account_number", unique=true, nullable=false)
	private String accountNumber;
	
	@NotBlank(message = "Bank Branch cannot be blank")
	@NotNull(message="Bank Branch cannot be null")
	@Column(name="bank_branch", nullable=false)
	@Enumerated(EnumType.STRING)
	private BankBranch bankBranch;
	
	@NotBlank(message = "IFSC Code cannot be blank")
	@NotNull(message="IFSC Code cannot be null")
	@Column(name="ifsc_code", nullable=false)
	@Enumerated(EnumType.STRING)
	private IFSCCode ifscCode;
	
	@NotNull(message="Total Balance cannot be null")
	@Column(name="total_balance", nullable=false)
	private BigDecimal totalBalance;
	
	@NotBlank(message = "Account Type cannot be blank")
	@NotNull(message="Account Type cannot be null")
	@Column(name="account_type", nullable=false)
	@Enumerated(EnumType.STRING)
	private AccountType accountType;
	
	//One Customer Many Accounts Relationship
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;
    
    //One Account many Beneficiary Relationship
    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Beneficiary> beneficiaries = new HashSet<>();
    
    //One Account many Transaction Relationship
    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Transaction> transactions = new HashSet<>();

	public Account() {}

	public Account(Long accountId, String accountNumber, BankBranch bankBranch, IFSCCode ifscCode, BigDecimal totalBalance, AccountType accountType, Customer customer) {
		super();
		this.accountId = accountId;
		this.accountNumber = accountNumber;
		this.bankBranch = bankBranch;
		this.ifscCode = ifscCode;
		this.totalBalance = totalBalance;
		this.accountType = accountType;
		this.customer = customer;
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

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
    
}
