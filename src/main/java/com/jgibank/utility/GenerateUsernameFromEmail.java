package com.jgibank.utility;

public class GenerateUsernameFromEmail {
	public static String generateUsernameFromEmail(String email) {
        String[] parts = email.split("@");
        String username = parts[0];
        return username;
    }
}
