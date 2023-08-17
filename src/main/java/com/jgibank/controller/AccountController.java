package com.jgibank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jgibank.dto.JwtTokenResponse;
import com.jgibank.entity.Account;
import com.jgibank.service.AccountService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/v1")
public class AccountController {
	
	@Autowired
	private AccountService accountService;
	
   //Create Account
	@PostMapping("account")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> addAccount(@RequestBody @Valid Account account) {
		try {
			return ResponseEntity.status(HttpStatus.CREATED).body(accountService.addAccount(account));
		} catch (Exception e) {
           return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e);
		}
	}
}
