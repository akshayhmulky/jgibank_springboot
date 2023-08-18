package com.jgibank.dto;

import java.math.BigDecimal;

public class FundTransferDTO {

	private Long beneficiaryId;
	private BigDecimal amountToTransfer;
	private Long accountId;
	
	
	public Long getBeneficiaryId() {
		return beneficiaryId;
	}
	public void setBeneficiaryId(Long beneficiaryId) {
		this.beneficiaryId = beneficiaryId;
	}
	public BigDecimal getAmountToTransfer() {
		return amountToTransfer;
	}
	public void setAmountToTransfer(BigDecimal amountToTransfer) {
		this.amountToTransfer = amountToTransfer;
	}
	public Long getAccountId() {
		return accountId;
	}
	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}
	
	
		
}
