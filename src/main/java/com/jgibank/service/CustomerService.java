package com.jgibank.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.jgibank.repository.CustomerRepository;

//Handles Registration, SignInwithJWTtoken, Profile updation
@Service
public class CustomerService implements UserDetailsService{

	@Autowired
	private CustomerRepository customerRepository;
	
	
	//We can use this method to check if username is already present while registering a user in controller
	//Or we can use already existing method present below
//    public Optional<Customer> findByUsername(String email){
//        return customerRepository.findByUsername(email);
//    }
	
	
    //Registering user
    public String registerUser(Customer request){
        request.setPassword(passwordEncoder.encode(request.getPassword()));
        request.setRole(Role.ROLE_USER);  //Will come back to make this dynamic
        userRepository.save(request);
        return "User Register Successfully!!";
    }
	
	
	
	
	
	
	//Used by Spring Security to load the user during authentication
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		return customerRepository.findByUsername(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
	}
  
	
	
	
	
	
}
