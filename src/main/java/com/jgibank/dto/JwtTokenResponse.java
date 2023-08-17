package com.jgibank.dto;

import com.jgibank.entity.Account;

public class JwtTokenResponse {
  private String token;

public String getToken() {
	return token;
}

public void setToken(String token) {
	this.token = token;
}

public JwtTokenResponse(String token) {
	super();
	this.token = token;
}
  
}


