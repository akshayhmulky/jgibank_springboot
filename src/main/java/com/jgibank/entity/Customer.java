package com.jgibank.entity;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.jgibank.entity.enums.Gender;
import com.jgibank.entity.enums.Role;
import com.jgibank.utility.GenerateUsernameFromEmail;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="customer")
public class Customer implements UserDetails{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="customer_id", nullable=false)
	private Long customerId;
	
	@Column(name="full_name", nullable=false)
	@NotBlank(message = "FullName cannot be blank")
	@NotNull(message="FullName cannot be null")
	private String fullName;

	@Column(name="username", unique=true, nullable=false)
	private String username;  //Username will be auto-generated based on email address
	
	@Column(name="email", nullable=false, unique=true)
	@NotBlank(message = "Email cannot be blank")
	@NotNull(message="Email cannot be null")
	@Email(message="Please enter valid email")
	private String email; 
	
	@Column(name="password", nullable=false)
	@NotBlank(message = "Password cannot be blank")
	@NotNull(message="Password cannot be null")
	private String password;
	
	@Column(name="address", nullable=false)
	@NotBlank(message = "Address cannot be blank")
	@NotNull(message="Address cannot be null")
	private String address;
	
	@Column(name="phone_number", nullable=false)
	@NotBlank(message = "Phone Number cannot be blank")
	@NotNull(message="Phone Number cannot be null")
	@Size(min = 10, max = 10, message = "Phone number must be 10 digits")
	private String phoneNumber;
	
	@Column(name="gender", nullable=false)
	@NotNull(message="Gender cannot be null")
	@Enumerated(EnumType.STRING)
	private Gender gender;
   
	@Column(name="role", nullable=false)
	@Enumerated(EnumType.STRING)
	private Role role;
	
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Account> accounts = new HashSet<>();
    
    public Customer() { }

    public Customer(String fullName, String email, String username, String password, String address, String phoneNumber, Gender gender, Role role){
        this.fullName = fullName;
        this.email = email;
        this.username=username;
        this.password = password;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
        this.role = role;
    }
    
	
	//Getters and Setters
    

	public String getFullName() {
		return fullName;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public Set<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(Set<Account> accounts) {
		this.accounts = accounts;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
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


	@Override
	@JsonIgnore
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}
	
	@JsonProperty
	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String getUsername() {
		return username;
	}	

	public void setUsername(String username) {
		this.username=username;
	}
	

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	//
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of(new SimpleGrantedAuthority(role.name()));
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
