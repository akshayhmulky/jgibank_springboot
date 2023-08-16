package com.jgibank.entity;

import java.math.BigDecimal;

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
    @Column(name="beneficiary_name")
	private String beneficiaryName;
    
    @NotNull(message="Beneficiary Bank name should not be null")
    @NotBlank(message="Beneficiary Bank name should not be blank")
    @Column(name="beneficiary_bank_name")
	private String beneficiaryBankName;
    
    @Column(name="beneficiary_account_number")
    @NotNull(message="Beneficiary Account Number Should not be null")
    @NotBlank(message="Beneficiary Account number should not be blank")
	private String beneficiaryAccountNumber;
	

    @NotNull(message = "maximum_transfer_limit must not be null")
    @Column(name="maximum_transfer_limit")
	private BigDecimal maximumTransferLimit ;
    
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account;
    
    
    public Beneficiary() {
    }

    public Beneficiary(
            String beneficiaryName,
            String beneficiaryBankName,
            String beneficiaryAccountNumber,
            BigDecimal maximumTransferLimit,
            Account account
    ) {
        this.beneficiaryName = beneficiaryName;
        this.beneficiaryBankName = beneficiaryBankName;
        this.beneficiaryAccountNumber = beneficiaryAccountNumber;
        this.maximumTransferLimit = maximumTransferLimit;
        this.account = account;
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

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

}