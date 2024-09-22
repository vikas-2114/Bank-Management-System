// AccountService

package com.bms.service;

import com.bms.Account;
import com.bms.Register;
import com.bms.dao.AccountDao;

import java.util.Scanner;

public class AccountService {
    private AccountDao accountDao;
    private Scanner scanner;
    private static long lastAccountNumber = 1001000; // Starting account number

    public AccountService(AccountDao accountDao, Scanner scanner) {
        this.accountDao = accountDao;
        this.scanner = scanner;
    }

    private synchronized long generateNextAccountNumber() {
        // Increment the last account number and return the new value
        lastAccountNumber += 1;
        return lastAccountNumber;
    }

    public long openAccount(Register register) {
        // Check if an account with the same email already exists
        if (accountDao.getAccountByEmail(register.getEmail()) != null) {
            System.out.println("Account already exists with this email.");
            return -1;
        }

        // Collect account details from user
        scanner.nextLine(); // Consume newline left-over
        System.out.print("Enter Full Name: ");
        String fullName = scanner.nextLine();
        System.out.print("Enter Address: ");
        String address = scanner.nextLine();
        System.out.print("Enter Date of Birth (yyyy-mm-dd): ");
        String dateOfBirth = scanner.nextLine();
        System.out.print("Enter Account Type (Saving / Current): ");
        String accountType = scanner.nextLine();
        System.out.print("Enter Aadhar Number: ");
        long aadharNo = scanner.nextLong();
        scanner.nextLine(); // Consume newline left-over
        System.out.print("Enter Pancard Number: ");
        String pancardNo = scanner.nextLine();

        // Validate initial amount
        double balance;
        while (true) {
            System.out.print("Enter Initial Amount (must be at least 500): ");
            balance = scanner.nextDouble();
            scanner.nextLine(); // Consume newline left-over

            if (balance >= 500) {
                break; // Exit the loop if the amount is valid
            } else {
                System.out.println("Initial amount must be at least 500. Please try again.");
            }
        }

        // Collect the security pin without hashing
        System.out.print("Enter Security Pin (4 digits): ");
        String securityPin = scanner.nextLine();

        // Validate that the pin is exactly 4 digits
        while (securityPin.length() != 4 || !securityPin.matches("\\d{4}")) {
            System.out.println("Invalid Pin! Please enter a 4-digit pin.");
            System.out.print("Enter Security Pin (4 digits): ");
            securityPin = scanner.nextLine();
        }

        // Generate a new unique account ID
        long newAccountID = generateNextAccountID();

        // Create and set up a new Account object
        Account account = new Account();
        account.setAccountID(newAccountID); // Set the generated account ID
        account.setName(fullName);
        account.setEmail(register.getEmail());
        account.setPassword(register.getPassword()); // Use the password from Register if necessary
        account.setContactNo(register.getContactNo()); // Use the contact number from Register
        account.setAddress(address);
        account.setDateOfBirth(dateOfBirth);
        account.setAccountType(accountType);
        account.setAadharNo(aadharNo);
        account.setPancardNo(pancardNo);
        account.setBalance(balance); // Set initial balance
        account.setSecurityPin(securityPin); // Set the security pin directly without hashing

        // Save the new account to the database
        accountDao.saveAccount(account);

        // Return the newly generated accountID
        return newAccountID;
    }


    private long generateNextAccountID() {
		// TODO Auto-generated method stub
		return 0;
	}

	public long getAccountIDByEmail(String email) {
        // Retrieve the account based on the email from the database
        Account account = accountDao.getAccountByEmail(email);

        if (account != null) {
            // Fetch the current account ID
            long currentAccountID = account.getAccountID();

            // Print the current account ID
          //  System.out.println("Account ID for email " + email + ": " + currentAccountID);

            // Return the current account ID
            return currentAccountID;
        } else {
            // Inform that no account was found for the provided email
            //System.out.println("No account found with email: " + email);

            // Return -1 to indicate that the account was not found
            return -1;
        }
    }}

