package com.jgibank.service;

import java.util.List;
import java.util.Set;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.jgibank.dto.CustomerProfileUpdateRequestDTO;
import com.jgibank.dto.CustomerResponseDTO;
import com.jgibank.entity.Account;
import com.jgibank.entity.Customer;
import com.jgibank.entity.enums.Role;
import com.jgibank.repository.CustomerRepository;
import com.jgibank.utility.GenerateUsernameFromEmail;

@Service
public class CustomerService implements UserDetailsService {

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtService jwtService;

	// Registering user
	public String registerUser(Customer customer) {
		try {
			customer.setPassword(passwordEncoder.encode(customer.getPassword()));
			customer.setUsername(GenerateUsernameFromEmail.generateUsernameFromEmail(customer.getEmail()));
			customer.setRole(Role.ROLE_USER); // By default Role will be ROLE_USER
			customerRepository.save(customer);
			return "User Register Successfully!!";
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityViolationException("User already exists!!");
		} catch (Exception e) {
			return "An error occurred while processing the request: " + e;
		}
	}

	// Login User
	public String loginUser(Customer request) {
		try {

			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

			var user = customerRepository.findByUsername(request.getUsername())
					.orElseThrow(() -> new IllegalArgumentException());

			return jwtService.generateToken(user);
		} catch (BadCredentialsException e) {
			throw new BadCredentialsException("Invalid username or password");
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("User not Found!!");
		} catch (Exception e) {
			return "An error occurred while processing the request: " + e;
		}
	}

	
	// Get account by username
	public Set<Account> getAccountsByUsername(String username) {
		Customer customer = customerRepository.findByUsername(username).orElse(null);
		Set<Account> accounts = customer.getAccounts();
		return accounts;
	}
	
	
	// Get all accounts (FOR ADMIN)
	public List<Customer> getAllCustomers() {
		return customerRepository.findAll();
	}
	

	// Get customer by username
	public Customer getCustomerByUsername(String username) {
		return customerRepository.findByUsername(username).orElse(null);
	}
	
	//Get customer by username(exclusing sensitive details)
//	public CustomerResponseDTO getCustomerByUsernameExcludingSensitiveDetails(String username) {
//		return convertEntityToDTO(customerRepository.findByUsername(username).orElse(null));
//	}
//	
	
	public CustomerResponseDTO getCustomerByUsernameExcludingSensitiveDetails(String username) {
	return convertEntityToDTO(customerRepository.findByUsername(username).orElse(null));
}
	
	
	public String changeRoleOfCustomerByUsername(String username, String role) {
		Customer customer = getCustomerByUsername(username);
		customer.setRole(role.equals("ROLE_USER")?Role.ROLE_USER:Role.ROLE_ADMIN);
		customerRepository.save(customer);
		return "Role Change successfully";
	}
	
	

	// Update Customer profile by username
	public CustomerResponseDTO updateCustomerByUsername(CustomerProfileUpdateRequestDTO customer) {
		Customer existingCustomer = getCustomerByUsername(customer.getUsername());
		if (customer.getUsername() == null || existingCustomer == null) {
			throw new IllegalArgumentException("Either username is blank or not valid!");
		}
		if (customer.getAddress() != null) {
			existingCustomer.setAddress(customer.getAddress());
		}
		
		if(customer.getPassword() != null) {
			existingCustomer.setPassword(passwordEncoder.encode(customer.getPassword()));
		}
		
		if (customer.getFullName() != null) {
			existingCustomer.setFullName(customer.getFullName());
		}
		if (customer.getPhoneNumber() != null) {
			existingCustomer.setPhoneNumber(customer.getPhoneNumber());
		}
		if (customer.getGender() != null) {
			existingCustomer.setGender(customer.getGender());
		}
		return convertEntityToDTO(customerRepository.save(existingCustomer));
	}

	
	
	// Convert Customer to CustomerResponseDTO so that we can only show the fields which are necessary, and hide confidential fields
	public CustomerResponseDTO convertEntityToDTO(Customer customer) {
		CustomerResponseDTO customerResponseDTO = new CustomerResponseDTO();
		customerResponseDTO.setCustomerId(customer.getCustomerId());
		customerResponseDTO.setFullName(customer.getFullName());
		customerResponseDTO.setAddress(customer.getAddress());
		customerResponseDTO.setEmail(customer.getEmail());
		customerResponseDTO.setGender(customer.getGender());
		customerResponseDTO.setPhoneNumber(customer.getPhoneNumber());
		customerResponseDTO.setUsername(customer.getUsername());
		customerResponseDTO.setAccount(customer.getAccounts());
		return customerResponseDTO;
	}

	
	
	// Used by Spring Security to load the user during authentication
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return customerRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("User not found"));
	}

}
