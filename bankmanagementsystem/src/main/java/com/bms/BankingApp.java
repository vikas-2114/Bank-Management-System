package com.bms;

import com.bms.dao.AccountDao;
import com.bms.dao.RegisterDao;
import com.bms.dao.TransactionnDao;
import com.bms.dao.imp.AccountDaoImp;
import com.bms.dao.imp.RegisterDaoImp;
import com.bms.dao.imp.TransactionnDaoImp;
import com.bms.exception.AdminException;
import com.bms.service.AccountManagerService;
import com.bms.service.AccountService;
import com.bms.service.UserService;
import com.bms.service.AdminService;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;
import java.util.Scanner;

public class BankingApp {
    public static void main(String[] args) {
        // Initialize SessionFactory
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Scanner scanner = new Scanner(System.in);

        // Initialize DAOs
        RegisterDao registerDao = new RegisterDaoImp(sessionFactory);
        AccountDao accountDao = new AccountDaoImp(sessionFactory);
        TransactionnDao transactionnDao = new TransactionnDaoImp(sessionFactory);

        // Initialize Services
        UserService userService = new UserService(registerDao, scanner);
        AccountService accountService = new AccountService(accountDao, scanner);
        AccountManagerService accountManagerService = new AccountManagerService(accountDao, transactionnDao, scanner);
        AdminService adminService = new AdminService(accountDao, transactionnDao);

        try {
            while (true) {
                System.out.println("*** WELCOME TO BANK MANAGEMENT SYSTEM ***");
                System.out.println();
                System.out.println("1. Admin Login");
                System.out.println("2. User Login");
                System.out.println("3. Register");
                System.out.println("4. Exit");
                System.out.print("Enter your choice: ");
                int choice1 = scanner.nextInt();
                scanner.nextLine(); // Consume the newline

                switch (choice1) {
                    case 1:
                        handleAdminLogin(scanner, adminService);
                        break;

                    case 2:
                        handleUserLogin(scanner, accountService, accountManagerService, userService);
                        break;

                    case 3:
                        userService.registerUser();
                        break;

                    case 4:
                        System.out.println("THANK YOU FOR USING BANKING SYSTEM!!!");
                        System.out.println("Exiting System!");
                        return; // Exit the program

                    default:
                        System.out.println("Invalid choice! Please select a valid option.");
                        break;
                }
            }
        } finally {
            scanner.close(); // Close the scanner
            sessionFactory.close(); // Close the SessionFactory
        }
    }

    private static void handleAdminLogin(Scanner scanner, AdminService adminService) {
        System.out.print("Enter Admin Username: ");
        String adminUsername = scanner.nextLine();
        System.out.print("Enter Admin Password: ");
        String adminPassword = scanner.nextLine();
        try {
            if (adminService.authenticate(adminUsername, adminPassword)) {
                System.out.println("Admin Logged In!");
                int adminChoice = 0;
                while (adminChoice != 4) {
                    System.out.println();
                    System.out.println("1. View All Users");
                    System.out.println("2. View All Transactions");
                    System.out.println("3. Delete User Account");
                    System.out.println("4. Log Out");
                    System.out.print("Enter your choice: ");
                    adminChoice = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline
                    switch (adminChoice) {
                        case 1:
                            List<Account> accounts = adminService.getAllAccounts();
                            if (accounts.isEmpty()) {
                                System.out.println("No accounts found.");
                            } else {
                                accounts.forEach(account -> System.out.println(account));
                            }
                            break;
                        case 2:
                            List<Transactionn> transactions = adminService.getAllTransactions();
                            if (transactions.isEmpty()) {
                                System.out.println("No transactions found.");
                            } else {
                                transactions.forEach(transaction -> System.out.println(transaction));
                            }
                            break;
                        case 3:
                            System.out.print("Enter the Email ID of the account to delete: ");
                            String emailToDelete = scanner.nextLine();
                            boolean deleted = adminService.deleteAccountByEmail(emailToDelete);
                            if (deleted) {
                                System.out.println("Account deleted successfully.");
                            } else {
                                System.out.println("Account not found.");
                            }
                            break;
                        case 4:
                            System.out.println("Logging out...");
                            break;
                        default:
                            System.out.println("Invalid choice! Please select a valid option.");
                            break;
                    }
                }
            } else {
                System.out.println("Invalid admin credentials!");
            }
        } catch (AdminException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void handleUserLogin(Scanner scanner, AccountService accountService, AccountManagerService accountManagerService, UserService userService) {
        Register register = userService.login();
        if (register != null) {
            System.out.println();
            System.out.println("User Logged In!");
            String email = register.getEmail();
            long accountID = accountService.getAccountIDByEmail(email);

            if (accountID == -1) {
                System.out.println();
                System.out.println("1. Open a new Bank Account");
                System.out.println("2. Exit");
                int choice2 = scanner.nextInt();
                scanner.nextLine(); // Consume the newline
                if (choice2 == 1) {
                    accountID = accountService.openAccount(register);
                    if (accountID != -1) {
                        System.out.println("Account Created Successfully");
                        System.out.println("Your Account ID is: " + accountID);
                    } else {
                        System.out.println("Failed to create account. Please try again.");
                    }
                }
            }

            int choice3 = 0;
            while (choice3 != 5) {
                System.out.println();
                System.out.println("1. Withdraw Money");
                System.out.println("2. Deposit Money");
                System.out.println("3. Transfer Money");
                System.out.println("4. Check Balance");
                System.out.println("5. Log Out");
                System.out.print("Enter your choice: ");
                choice3 = scanner.nextInt();
                scanner.nextLine(); // Consume the newline

                switch (choice3) {
                    case 1:
                        accountManagerService.debitMoney(email);
                        break;

                    case 2:
                        accountManagerService.creditMoney(email);
                        break;

                    case 3:
                        accountManagerService.transferMoney(email);
                        break;

                    case 4:
                        accountManagerService.getBalance(email);
                        break;

                    case 5:
                        System.out.println("Logged Out Successfully!");
                        break;

                    default:
                        System.out.println("Invalid choice! Please select a valid option.");
                        break;
                }
            }
        } else {
            System.out.println("Incorrect Email or Password!");
        }
    }
}
