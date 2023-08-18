package com.jgibank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jgibank.dto.FundTransferDTO;
import com.jgibank.service.TransactionService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/v1")
public class TransactionController {
	
	@Autowired
	 private TransactionService transactionService;
	
	/*
	 * Route: GET http://localhost:8080/api/v1/transactions/<accountNumber>
	 * Description: Get All transactions by AccountNumber
	 */
	@GetMapping("transaction/{accountNumber}")
	public ResponseEntity<?> getAccountByAccountNumber(@PathVariable("accountNumber") String accountNumber) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(transactionService.getAllTransactionByAccountId(accountNumber));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e);
		}
	}
	
	
	/*
	 * Route: POST http://localhost:8080/api/v1/account/fundtransfer 
	 * Description: Transferring fund
	 * body: 
		  { "beneficiaryId":1,
		    "deposit":1000, 
		    "accountId":2
 			}
	 */
	@PostMapping("account/fundtransfer")
	public ResponseEntity<?> fundTransfer(@RequestBody @Valid FundTransferDTO fundData){
		try {
			return ResponseEntity.status(HttpStatus.CREATED).body(transactionService.fundTransfer(fundData));
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	
	
	

}
