package com.jgibank.entity;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class Beneficiary {
    @Id 
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="beneficiary_id", nullable=false)
	private Long beneficiaryId;
    

    @NotNull(message="Beneficiary name should not be null")
    @NotBlank(message="Beneficiary name should not be blank")
    @Column(name="beneficiary_name", nullable=false)
	private String beneficiaryName;
    
    //NOTE: Currently only allowing same Bank transfer, hence its default
    @Column(name="beneficiary_bank_name", nullable=false)
	private String beneficiaryBankName;
    
    @Column(name="beneficiary_account_number", nullable=false)
    @NotNull(message="Beneficiary Account Number Should not be null")
    @NotBlank(message="Beneficiary Account number should not be blank")
	private String beneficiaryAccountNumber;
	

    @NotNull(message = "maximum_transfer_limit must not be null")
    @Column(name="maximum_transfer_limit", nullable=false)
	private BigDecimal maximumTransferLimit ;
    
    
    @Column(name = "account_id", nullable = false)
    @NotNull(message="AccountId cannot be null")
    private Long accountId;
    
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id" , referencedColumnName = "account_id", insertable = false, updatable = false)
    private Account account;
    
    
    public Beneficiary() {
    }

    public Beneficiary(
            String beneficiaryName,
            String beneficiaryBankName,
            String beneficiaryAccountNumber,
            BigDecimal maximumTransferLimit,
            Long accountId
    ) {
        this.beneficiaryName = beneficiaryName;
        this.beneficiaryBankName = beneficiaryBankName;
        this.beneficiaryAccountNumber = beneficiaryAccountNumber;
        this.maximumTransferLimit = maximumTransferLimit;
        this.accountId = accountId;
    }
    
    // Getters and Setters

    public Long getBeneficiaryId() {
        return beneficiaryId;
    }

    public void setBeneficiaryId(Long beneficiaryId) {
        this.beneficiaryId = beneficiaryId;
    }

    public String getBeneficiaryName() {
        return beneficiaryName;
    }

    public void setBeneficiaryName(String beneficiaryName) {
        this.beneficiaryName = beneficiaryName;
    }

    public String getBeneficiaryBankName() {
        return beneficiaryBankName;
    }

    public void setBeneficiaryBankName(String beneficiaryBankName) {
        this.beneficiaryBankName = beneficiaryBankName;
    }

    public String getBeneficiaryAccountNumber() {
        return beneficiaryAccountNumber;
    }

    public void setBeneficiaryAccountNumber(String beneficiaryAccountNumber) {
        this.beneficiaryAccountNumber = beneficiaryAccountNumber;
    }

    public BigDecimal getMaximumTransferLimit() {
        return maximumTransferLimit;
    }

    public void setMaximumTransferLimit(BigDecimal maximumTransferLimit) {
        this.maximumTransferLimit = maximumTransferLimit;
    }

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	
    

}