package com.jgibank.dto;

import java.math.BigDecimal;

public class BeneficiaryDTO {

    private Long beneficiaryId;
    private String beneficiaryName;
    private String beneficiaryBankName;
    private String beneficiaryAccountNumber;
    private BigDecimal maximumTransferLimit;
    private Long accountId;
    
    // Constructors, getters, and setters

    public BeneficiaryDTO() {}

    public BeneficiaryDTO(Long beneficiaryId, String beneficiaryName, String beneficiaryBankName, String beneficiaryAccountNumber, BigDecimal maximumTransferLimit, Long accountId) {
        this.beneficiaryId = beneficiaryId;
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
