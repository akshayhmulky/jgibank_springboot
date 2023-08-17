package com.jgibank.controller;

import java.util.Set;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jgibank.dto.CustomerProfileUpdateDTO;
import com.jgibank.dto.JwtTokenResponse;
import com.jgibank.entity.Account;
import com.jgibank.entity.Customer;
import com.jgibank.service.CustomerService;
import com.jgibank.utility.GenerateBankAccountNumber;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/v1")
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;

	@GetMapping("test")
	public ResponseEntity<?> testApi(){
		return ResponseEntity.status(HttpStatus.OK).body(GenerateBankAccountNumber.generateBankAccountNumber());
	}
	
	@PostMapping("register")
    public ResponseEntity<String> registerUser(@RequestBody @Valid Customer customer){
		try {
			return ResponseEntity.status(HttpStatus.CREATED).body(customerService.registerUser(customer));
		} catch (Exception e) {
           return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
    }
	
    @PostMapping("login")
    public ResponseEntity<?> loginUser(@RequestBody Customer customer){
    	try {
    		return ResponseEntity.status(HttpStatus.OK).body(new JwtTokenResponse(customerService.loginUser(customer)));
			
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}    
    }
	
    //Customer would only be able to see their Accounts, however ADMIN can see everyone's accounts
	@GetMapping("accounts/{username}")
	@PreAuthorize("hasRole('ADMIN') or (hasRole('USER') and #username == authentication.name)")
	public ResponseEntity<?> getAccountsByUsername(@PathVariable String username){
		try {
		return ResponseEntity.status(HttpStatus.OK).body(customerService.getAccountsByUsername(username));
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e);
		}
	}
    
	
	//Update Customer (NOTE: include username in body..)
	@PutMapping("update/customer")
	public ResponseEntity<?> updateCustomerByUsername(@RequestBody CustomerProfileUpdateDTO customer){
		try {
		 return ResponseEntity.status(HttpStatus.OK).body(customerService.updateCustomerByUsername(customer));

		} catch (Exception e) {
           return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	
	
	//ONLY FOR ADMIN
	@GetMapping("customers")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> getAllCustomers(){
		return ResponseEntity.status(HttpStatus.OK).body(customerService.getAllCustomers());
	}
	
	
}
