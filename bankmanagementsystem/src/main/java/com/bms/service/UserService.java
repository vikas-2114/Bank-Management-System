package com.bms.service;

import com.bms.dao.RegisterDao;
import com.bms.Register;

import java.util.Scanner;

public class UserService {
    private RegisterDao registerDao;
    private Scanner scanner;

    public UserService(RegisterDao registerDao, Scanner scanner) {
        this.registerDao = registerDao;
        this.scanner = scanner;
    }

    public void registerUser() {
        // Generate a new usernameID based on the last registered user
        int lastUsernameID = registerDao.getLastUsernameID(); // Implement this method in RegisterDao
        int newUsernameID = lastUsernameID + 1;

        System.out.println("Generated Username ID: " + newUsernameID);

        System.out.print("Enter Email: ");
        String email = scanner.nextLine();

        System.out.print("Enter Password (4 digits): ");
        int password = scanner.nextInt(); // Password is kept as int
        scanner.nextLine(); // Consume newline left-over

        System.out.print("Enter Contact Number: ");
        Long contactNo = scanner.nextLong();
        scanner.nextLine(); // Consume newline left-over

        // Create a new Register object with the generated usernameID
        Register register = new Register(newUsernameID, email, password, contactNo);

        // Save the Register object using RegisterDao
        registerDao.saveRegister(register);

        System.out.println("User registered successfully.");
    }

    public Register login() {
        System.out.print("Enter Email: ");
        String email = scanner.nextLine();

        System.out.print("Enter Password (4 digits): ");
        int password = scanner.nextInt();
        scanner.nextLine(); // Consume newline left-over

        // Fetch the Register object by email
        Register register = registerDao.getRegisterByEmail(email);

        // Check if the password matches
        if (register != null && register.getPassword() == password) {
            return register;
        } else {
            return null;
        }
    }
}
