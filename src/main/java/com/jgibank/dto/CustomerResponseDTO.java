package com.jgibank.dto;

import com.jgibank.entity.Account;
import com.jgibank.entity.enums.Gender;

import java.util.Set;

public class CustomerResponseDTO {
	private Long customerId;
	
	private String username;
	
	private String fullName;
	
	private String email;

	private String address;
	
	private String phoneNumber;
	
	private Gender gender;
	
	private Set<Account> account;

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public Set<Account> getAccount() {
		return account;
	}

	public void setAccount(Set<Account> account) {
		this.account = account;
	}

	public CustomerResponseDTO(Long customerId, String username, String email, String fullName, String address, String phoneNumber,
			Gender gender, Set<Account> account) {
		super();
		this.customerId = customerId;
		this.username = username;
		this.email = email;
		this.fullName = fullName;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.gender = gender;
		this.account = account;
	}

	public CustomerResponseDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
