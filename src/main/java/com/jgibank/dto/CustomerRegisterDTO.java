package com.jgibank.dto;

import java.util.Set;

import com.jgibank.entity.Account;
import com.jgibank.entity.enums.Gender;
import com.jgibank.entity.enums.Role;

public class CustomerRegisterDTO {
    private Long customerId;
    private String fullName;
    private String email;
    private String password;
    private String address;
    private String phoneNumber;
    private Gender gender;
    private Role role;
    
    //Constructor
    public CustomerRegisterDTO() {
    }

    public CustomerRegisterDTO(Long customerId, String fullName, String email, String password, String address, String phoneNumber, Gender gender, Role role, Set<AccountDTO> accounts) {
        this.customerId = customerId;
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
        this.role = role;
    }

    // Getters and Setters

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    
    public String getPassword() {
    	return password;
    }
    
    public void setPassword(String password) {
    	this.password = password;
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

}
