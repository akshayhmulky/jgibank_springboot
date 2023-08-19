package com.jgibank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jgibank.dto.AddBeneficiaryRequestDTO;
import com.jgibank.entity.Beneficiary;
import com.jgibank.service.BeneficiaryService;

@RestController
@RequestMapping("api/v1")
@CrossOrigin(origins = "http://localhost:3000") 
//@CrossOrigin(origins = "https://jgibank-react.vercel.app")
public class BeneficiaryController {

  @Autowired
  private BeneficiaryService beneficiaryService;	
  
  
	/*
	 * Route: POST http://localhost:8080/api/v1/beneficiary/add
	 * Description: Allow customers to add beneficiary
	 * body: 
		{
		    "beneficiaryName":"John Doe",
		    "beneficiaryAccountNumber": "JGI0000066628632",
		    "maximumTransferLimit":"200000",
		    "accountId":1
		}
	   Token Authentication: Bearer <JWTToken>
	 */
  @PostMapping("beneficiary/add")
  public ResponseEntity<?> addBeneficiary(@RequestBody AddBeneficiaryRequestDTO beneficiary){
		try {
			return ResponseEntity.status(HttpStatus.CREATED).body(beneficiaryService.addBeneficiary(beneficiary));
		} catch (Exception e) {
           return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage()); //
		}
  }
	
  
  
  
  
  
  
}
