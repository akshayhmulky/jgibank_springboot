package com.jgibank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Set;

import com.jgibank.repository.TransactionRepository;

import jakarta.transaction.Transactional;

import com.jgibank.dto.FundTransferDTO;
import com.jgibank.entity.Transaction;

@Service
public class TransactionService {

	@Autowired
	private TransactionRepository transactionRepository;

	@Autowired
	private AccountService accountService;

	@Autowired
	private BeneficiaryService beneficiaryService;

	// Get all transaction by AccountId
	public Set<Transaction> getAllTransactionByAccountId(String accountNumber) {

		return accountService.getAccountByAccountNumber(accountNumber).getTransactions();
	}

	// Fund transfer
	@Transactional
	public String fundTransfer(FundTransferDTO fundData) {
		try {
			String transferFrom = accountService.getAccountNumberByAccountId(fundData.getAccountId());
			String transferTo = beneficiaryService.getAccountNumberFromBeneficiaryId(fundData.getBeneficiaryId());
            
			// Sender
			BigDecimal maximumTransferLimitOfSender = beneficiaryService.getMaximumTransferLimit(fundData.getBeneficiaryId());
			BigDecimal senderDeposit = new BigDecimal(0);
			BigDecimal senderWithdraw = fundData.getAmountToTransfer();
			int compareResult = senderWithdraw.compareTo(maximumTransferLimitOfSender);
			if(compareResult>0) {
				throw new IllegalArgumentException("Transferring fund is more than maximum Transfer Limit");
			}
			BigDecimal senderBalance = accountService.deductBalance(transferFrom, senderWithdraw);
			Long senderAccountId = fundData.getAccountId();
			Transaction senderTransaction = new Transaction();
			senderTransaction.setTransferFrom(transferFrom);
			senderTransaction.setTransferTo(transferTo);
			senderTransaction.setWithdraw(senderWithdraw);
			senderTransaction.setDeposit(senderDeposit);
			senderTransaction.setBalance(senderBalance);
			senderTransaction.setAccountId(senderAccountId);
			

			// Receiver
			Long receiverAccountId = accountService.getAccountIdByAccountNumber(transferTo);
			BigDecimal receiverWithdraw = new BigDecimal(0);
			BigDecimal receiverDeposit = fundData.getAmountToTransfer();
			BigDecimal receiverBalance = accountService.addBalance(transferTo, receiverDeposit);
			Transaction receiverTransaction = new Transaction();
			receiverTransaction.setTransferFrom(transferFrom);
			receiverTransaction.setTransferTo(transferTo);
			receiverTransaction.setWithdraw(receiverWithdraw);
			receiverTransaction.setDeposit(receiverDeposit);
			receiverTransaction.setBalance(receiverBalance);
			receiverTransaction.setAccountId(receiverAccountId);
			
			transactionRepository.save(senderTransaction);
			transactionRepository.save(receiverTransaction);

			return "Fun transfer Successful!!";

		} catch (Exception e) {
			throw new IllegalArgumentException(e.getMessage());
		}

	}

}
