package com.jgibank.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jgibank.dto.AddBalanceRequestDTO;
import com.jgibank.entity.Account;
import com.jgibank.service.AccountService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/v1")
//@CrossOrigin(origins = "http://localhost:3000")
@CrossOrigin(origins = "https://jgibank-react-i4yvxndzx-akshayhmulky.vercel.app")
public class AccountController {

	@Autowired
	private AccountService accountService;

	/*
	 * Route: POST http://localhost:8080/api/v1/account/add 
	 * Description: Allow only ADMIN to open account for the customer 
	 * body: 
		  { "bankBranch":"CHEMBUR",
		    "ifscCode":"JGIBANK003", 
		    "minimumBalance":10000, 
		    "accountType":"SAVINGS",
		    "customerId": 1 }
	 */
	@PostMapping("account/add")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> addAccount(@RequestBody @Valid Account account) {
		try {
			return ResponseEntity.status(HttpStatus.CREATED).body(accountService.addAccount(account));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e);
		}
	}

	/*
	 * Route: GET http://localhost:8080/api/v1/account/<accountNumber>
	 * Description: Get Account by AccountNumber
	 */

	@GetMapping("account/{accountNumber}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> getAccountByAccountNumber(@PathVariable("accountNumber") String accountNumber) {

		try {
			return ResponseEntity.status(HttpStatus.OK).body(accountService.getAccountByAccountNumber(accountNumber));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e);
		}
	}
	
	
	
	/*
	 * Route: GET http://localhost:8080/api/v1/account/addAmount/<accountNumber>
	 * Description: Add Amount to the account
	 */

	@PostMapping("account/addbalance")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> addBalance(@RequestBody AddBalanceRequestDTO addBalanceData) {

		try {
			accountService.addBalance(addBalanceData.getAccountNumber(), addBalanceData.getAmount());
			return ResponseEntity.status(HttpStatus.OK).body("Amount added successfully");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e);
		}
	}

}
