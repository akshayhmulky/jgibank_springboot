package com.jgibank;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.jgibank.entity.Customer;
import com.jgibank.entity.enums.Gender;
import com.jgibank.entity.enums.Role;
import com.jgibank.repository.CustomerRepository;

@SpringBootApplication
public class JgibankApplication {

	public static void main(String[] args) {
		SpringApplication.run(JgibankApplication.class, args);
	}
	
	//CREATE ADMIN USER BY DEFAULT FOR TESTING
	@Bean
	CommandLineRunner run(CustomerRepository customerRepository, PasswordEncoder passwordEncode){
		return args ->{
			if(customerRepository.findByRole(Role.ROLE_ADMIN).isPresent()) return;
			
			Customer admin = new Customer();
			admin.setFullName("Admin User");
			admin.setEmail("admin@gmail.com");
			admin.setUsername("admin");
			admin.setPassword(passwordEncode.encode("admin"));
			admin.setAddress("remote");
			admin.setPhoneNumber("1111111111");
			admin.setRole(Role.ROLE_ADMIN);
			admin.setGender(Gender.MALE);
			customerRepository.save(admin);
		};
	}

}
