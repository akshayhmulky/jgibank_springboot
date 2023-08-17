package com.jgibank.utility;

import java.util.Random;

public class GenerateBankAccountNumber {

    public static String generateBankAccountNumber() {
        Random random = new Random();
        StringBuilder accountNumber = new StringBuilder("JGI00000");
        
        for (int i = 0; i < 8; i++) {
            accountNumber.append(random.nextInt(10));  //Generates random number from 0 to 9 (8 times)
        }
        
        return accountNumber.toString();
    }
}
