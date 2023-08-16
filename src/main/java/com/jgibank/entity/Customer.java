package com.jgibank.entity;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.jgibank.entity.enums.Gender;
import com.jgibank.entity.enums.Role;

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
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name="customer")
public class Customer implements UserDetails{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="customer_id", nullable=false)
	private Long customerId;
	
	@NotBlank(message = "FullName cannot be blank")
	@NotNull(message="FullName cannot be null")
	@Column(name="full_name", nullable=false)
	private String fullName;

	@NotBlank(message = "Email cannot be blank")
	@NotNull(message="Email cannot be null")
	@Column(name="email", nullable=false, unique=true)
	private String email;  //We will use email as username
	
	@NotBlank(message = "Password cannot be blank")
	@NotNull(message="Password cannot be null")
	@Column(name="password", nullable=false)
	private String password;
	
	@NotBlank(message = "Address cannot be blank")
	@NotNull(message="Address cannot be null")
	@Column(name="address", nullable=false)
	private String address;
	
	@NotBlank(message = "Phone Number cannot be blank")
	@NotNull(message="Phone Number cannot be null")
	@Column(name="phone_number", nullable=false)
	private String phoneNumber;
	
	@NotBlank(message = "Gender cannot be blank")
	@NotNull(message="Gender cannot be null")
	@Column(name="gender", nullable=false)
	@Enumerated(EnumType.STRING)
	private Gender gender;
   
	@NotBlank(message = "Role cannot be blank")
	@NotNull(message="Role cannot be null")
	@Column(name="role", nullable=false)
	@Enumerated(EnumType.STRING)
	private Role role;
	
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Account> accounts = new HashSet<>();
    
    
    
    public Customer() {
    }

    public Customer(String fullName, String email, String password, String address, String phoneNumber, Gender gender, Role role){
        this.fullName = fullName;
        this.email = email;
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
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String getUsername() {
		return email;
	}	

	public void setUsername(String email) {
		this.email=email;
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
