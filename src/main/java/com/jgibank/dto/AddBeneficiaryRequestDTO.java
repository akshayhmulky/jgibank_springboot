package com.jgibank.dto;

import java.math.BigDecimal;

public class AddBeneficiaryRequestDTO {
	 private String beneficiaryName;
	 private String beneficiaryAccountNumber;
	 private BigDecimal maximumTransferLimit;
	 private String accountNumber;
	 private String beneficiaryBankName;
	 
	 
	 
	 
	public AddBeneficiaryRequestDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public AddBeneficiaryRequestDTO(String beneficiaryName, String beneficiaryAccountNumber,
			BigDecimal maximumTransferLimit, String accountNumber, String beneficiaryBankName) {
		super();
		this.beneficiaryName = beneficiaryName;
		this.beneficiaryAccountNumber = beneficiaryAccountNumber;
		this.maximumTransferLimit = maximumTransferLimit;
		this.accountNumber = accountNumber;
		this.beneficiaryBankName = beneficiaryBankName;
	}
	public String getBeneficiaryBankName() {
		return beneficiaryBankName;
	}
	public void setBeneficiaryBankName(String beneficiaryBankName) {
		this.beneficiaryBankName = beneficiaryBankName;
	}
	public String getBeneficiaryName() {
		return beneficiaryName;
	}
	public void setBeneficiaryName(String beneficiaryName) {
		this.beneficiaryName = beneficiaryName;
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
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	 
	 
}
