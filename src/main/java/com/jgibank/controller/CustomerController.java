package com.jgibank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jgibank.dto.CustomerProfileUpdateRequestDTO;
import com.jgibank.dto.JwtTokenResponse;
import com.jgibank.dto.RoleChangeDTORequest;
import com.jgibank.entity.Customer;
import com.jgibank.service.CustomerService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/v1")
//@CrossOrigin(origins = "http://localhost:3000") 
@CrossOrigin(origins = "https://jgibank-react.vercel.app")
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;

	//NOTE: ADMIN CREDENTIAL FOR TESTING:
	//username:admin
	//password:admin
	
	@GetMapping("bank")
	public ResponseEntity<?> testApi(@AuthenticationPrincipal Customer customer){
		
		return ResponseEntity.status(HttpStatus.OK).body("Hi, Welcome to JGI Bank..");
	}
	
	// User Registration
    /*
     * Route: POST http://localhost:8080/api/v1/register
     * Description: Allow users to register a new account
     * Request Body: {
		    "fullName":"Captain America",
		    "email": "captain123@gmail.com",
		    "password":"user@123",
		     "address":"Aundh, Pune, 411007",
		     "phoneNumber":"2222222222",
		     "gender":"MALE"
		}
		     */
	@PostMapping("register")
    public ResponseEntity<String> registerUser(@RequestBody @Valid Customer customer){
		try {
			return ResponseEntity.status(HttpStatus.CREATED).body(customerService.registerUser(customer));
		} catch (Exception e) {
           return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
    }
	
	
	   // User Login
    /*
     * Route: POST http://localhost:8080/api/v1/login
     * Description: Allow users to log in and obtain JWT Token
     * Request Body: {
		    "username":"captain123",
		    "password": "user@123"
		}
     */
    @PostMapping("login")
    public ResponseEntity<?> loginUser(@RequestBody Customer customer){
    	try {
    		return ResponseEntity.status(HttpStatus.OK).body(new JwtTokenResponse(customerService.loginUser(customer)));
			
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}    
    }
	
    // Get Accounts by Username
    /*
     * Route: GET http://localhost:8080/api/v1/accounts/{username}
     * Description: Allow only ADMIN and the authenticated user to view their accounts
     * Path Variable: username (String)
     * Token Authentication: Bearer <JWTToken>
     */
	@GetMapping("accounts/{username}")
	@PreAuthorize("hasRole('ADMIN') or (hasRole('USER') and #username == authentication.name)")
	public ResponseEntity<?> getAccountsByUsername(@PathVariable String username){
		try {
		return ResponseEntity.status(HttpStatus.OK).body(customerService.getAccountsByUsername(username));
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e);
		}
	}
    
	
    // Update Customer Profile
    /*
     * Route: PUT http://localhost:8080/api/v1/customer/update
     * Description: Allow authenticated users (both ADMIN and the user themselves) to update their profile information
     * Request Body: 
     * {
		    "fullName":"Captain America",
		    "address": "Chembur, acharya college",
		    "phoneNumber":"9999999999",
		    "gender":"MALE"
		}
		Token Authentication: Bearer <JWTToken>
     */
	@PutMapping("customer/update")
	public ResponseEntity<?> updateCustomerByUsername(@RequestBody CustomerProfileUpdateRequestDTO customer){
		try {
		 return ResponseEntity.status(HttpStatus.OK).body(customerService.updateCustomerByUsername(customer));

		} catch (Exception e) {
           return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	
	  // Get Customer by Username
    /*
     * Route: GET http://localhost:8080/api/v1/customer/{username}
     * Description: Allow only ADMIN and the authenticated user to view their profile
     * Path Variable: username (String)
     * Token Authentication: Bearer <JWTToken>
     */
	@GetMapping("customer/{username}")
	@PreAuthorize("hasRole('ADMIN') or (hasRole('USER') and #username == authentication.name)")
	public ResponseEntity<?> getCustomerByUsername(@PathVariable("username") String username){
		try {
		return ResponseEntity.status(HttpStatus.OK).body(customerService.getCustomerByUsernameExcludingSensitiveDetails(username));
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e);
		}


	}
	
	
    // Get All Customers (ADMIN only)
    /*
     * Route: GET http://localhost:8080/api/v1/customers
     * Description: Allow only ADMIN to view all customers
     * Token Authentication: Bearer <JWTToken>
     */
	@GetMapping("customers")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> getAllCustomers(){
		return ResponseEntity.status(HttpStatus.OK).body(customerService.getAllCustomers());
	}
	
	
	//Change role to ADMIN or from ADMIN to USER
	@PutMapping("customer/rolechange")
	@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> changeRoleOfCustomerByUsername(@RequestBody RoleChangeDTORequest body){
		try {
		return ResponseEntity.status(HttpStatus.OK).body(customerService.changeRoleOfCustomerByUsername(body.getUsername(), body.getRole()));
	    }catch(Exception e) {
		  return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e);
	    }
	}
	
	
	
}
