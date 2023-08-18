package com.jgibank.dto;

import java.util.Set;

import com.jgibank.entity.Account;
import com.jgibank.entity.enums.Gender;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class CustomerProfileUpdateRequestDTO {

	private Long customerId;
	
	private String username;
	
	private String fullName;

	private String password;

	private String address;
	
	private String phoneNumber;
	
	private Gender gender;
	

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username=username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public CustomerProfileUpdateRequestDTO(String fullName, String username, String password, String address, String phoneNumber,
			Gender gender) {
		super();
		this.fullName = fullName;
		this.username = username;
		this.password = password;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.gender = gender;
	}
	
	public CustomerProfileUpdateRequestDTO() {
		super();
		
	}

	public CustomerProfileUpdateRequestDTO(Set<Account> accountsByUsername) {
		// TODO Auto-generated constructor stub
	}
	
	
}
