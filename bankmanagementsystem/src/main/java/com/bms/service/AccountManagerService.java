// AccountManagerService.java

package com.bms.service;

import com.bms.Account;
import com.bms.Transactionn;
import com.bms.dao.AccountDao;
import com.bms.dao.TransactionnDao;
import com.bms.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.Scanner;

public class AccountManagerService {
    private AccountDao accountDao;
    private TransactionnDao transactionnDao;
    private Scanner scanner;
    private SessionFactory sessionFactory;

    public AccountManagerService(AccountDao accountDao, TransactionnDao transactionnDao, Scanner scanner) {
        this.accountDao = accountDao;
        this.transactionnDao = transactionnDao;
        this.scanner = scanner;
        this.sessionFactory = HibernateUtil.getSessionFactory(); // Initialize sessionFactory internally
    }

    private Account getAccountByEmail(String email) {
        return accountDao.getAccountByEmail(email);
    }

    public void creditMoney(String email) {
        scanner.nextLine(); // Clear the newline character from the buffer
        System.out.print("Enter Amount: ");
        double amount = scanner.nextDouble(); // Read the amount to credit
        scanner.nextLine(); // Clear the newline character from the buffer
        System.out.print("Enter Security Pin: ");
        String enteredPin = scanner.nextLine().trim(); // Read and trim the security pin

        Session session = null;
        Transaction transaction = null;

        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction(); // Begin a new transaction

            // Retrieve the account using the email
            Account account = getAccountByEmail(email);
            if (account != null) {
                // Check if the provided security pin matches the stored pin
                String storedPin = account.getSecurityPin().trim(); // Trim any leading/trailing spaces
                if (storedPin.equals(enteredPin)) {
                    // Update the account balance
                    account.setBalance(account.getBalance() + amount);
                    accountDao.updateAccount(account);

                    // Create a new transaction record
                    Transactionn transactionn = new Transactionn();
                    transactionn.setAmount(amount); // Set the amount for the credit transaction
                    transactionn.setAccountNumber(account.getAccountID()); // Set the account ID for the transaction
                    transactionn.setType("Credit"); // Set the type of transaction
                    transactionnDao.saveTransactionn(transactionn); // Save the transaction

                    transaction.commit(); // Commit the transaction
                    System.out.println("Rs." + amount + " deposited successfully.");
                } else {
                    System.out.println("Invalid Security Pin! Please try again.");
                }
            } else {
                System.out.println("Account not found!");
            }
        } catch (Exception e) {
            if (transaction != null) transaction.rollback(); // Rollback transaction in case of error
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close(); // Always close the session
            }
        }
    }

    public void debitMoney(String email) {
        scanner.nextLine(); // Clear the newline character from the buffer
        System.out.print("Enter Amount: ");
        double amount = scanner.nextDouble(); // Read the amount to debit
        scanner.nextLine(); // Clear the newline character from the buffer
        System.out.print("Enter Security Pin: ");
        String enteredPin = scanner.nextLine().trim(); // Read and trim the security pin

        Session session = null;
        Transaction transaction = null;

        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction(); // Begin a new transaction

            // Retrieve the account using the email
            Account account = getAccountByEmail(email);
            if (account != null) {
                // Check if the provided security pin matches the stored pin
                String storedPin = account.getSecurityPin().trim(); // Trim any leading/trailing spaces
                if (storedPin.equals(enteredPin)) {
                    // Check if the account has sufficient balance
                    if (amount <= account.getBalance()) {
                        // Update the account balance
                        account.setBalance(account.getBalance() - amount);
                        accountDao.updateAccount(account);
                      
                        // Create a new transaction record
                        Transactionn transactionn = new Transactionn();
                        transactionn.setAmount(amount); // Set the amount for the debit transaction
                        transactionn.setAccountNumber(account.getAccountID()); // Set the account ID for the transaction
                        transactionn.setType("Debit"); // Set the type of transaction
                        transactionnDao.saveTransactionn(transactionn); // Save the transaction

                        transaction.commit(); // Commit the transaction
                        System.out.println("Rs." + amount + " withdrawn successfully.");
                    } else {
                        System.out.println("Insufficient Balance!");
                    }
                } else {
                    System.out.println("Invalid Security Pin! Please try again.");
                }
            } else {
                System.out.println("Account not found!");
            }
        } catch (Exception e) {
            if (transaction != null) transaction.rollback(); // Rollback transaction in case of error
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close(); // Always close the session
            }
        }
    }

    public void transferMoney(String email) {
        scanner.nextLine(); // Clear the newline character from the buffer
        System.out.print("Enter Recipient Account ID: ");
        long recipientAccountID = scanner.nextLong(); // Read the recipient account ID
        scanner.nextLine(); // Clear the newline character from the buffer
        System.out.print("Enter Amount: ");
        double amount = scanner.nextDouble(); // Read the amount to transfer
        scanner.nextLine(); // Clear the newline character from the buffer
        System.out.print("Enter Security Pin: ");
        String enteredPin = scanner.nextLine().trim(); // Read and trim the security pin

        Session session = null;
        Transaction transaction = null;

        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction(); // Begin a new transaction

            // Retrieve the sender's account using the email
            Account senderAccount = getAccountByEmail(email);
            if (senderAccount != null) {
                // Check if the provided security pin matches the stored pin
                String storedPin = senderAccount.getSecurityPin().trim(); // Trim any leading/trailing spaces
                if (storedPin.equals(enteredPin)) {
                    // Check if the sender has sufficient balance
                    if (amount <= senderAccount.getBalance()) {
                        // Retrieve the recipient's account using the account ID
                        Account recipientAccount = accountDao.getAccountByID(recipientAccountID);
                        if (recipientAccount != null) {
                            // Update the sender's balance
                            senderAccount.setBalance(senderAccount.getBalance() - amount);
                            accountDao.updateAccount(senderAccount);

                            // Update the recipient's balance
                            recipientAccount.setBalance(recipientAccount.getBalance() + amount);
                            accountDao.updateAccount(recipientAccount);

                            // Create a new transaction record for the sender
                            Transactionn senderTransaction = new Transactionn();
                            senderTransaction.setAmount(amount); // Set the amount for the transfer transaction
                            senderTransaction.setAccountNumber(senderAccount.getAccountID()); // Set the sender's account ID for the transaction
                            senderTransaction.setType("Transfer - Debit"); // Set the type of transaction
                            transactionnDao.saveTransactionn(senderTransaction); // Save the transaction

                            // Create a new transaction record for the recipient
                            Transactionn recipientTransaction = new Transactionn();
                            recipientTransaction.setAmount(amount); // Set the amount for the transfer transaction
                            recipientTransaction.setAccountNumber(recipientAccount.getAccountID()); // Set the recipient's account ID for the transaction
                            recipientTransaction.setType("Transfer - Credit"); // Set the type of transaction
                            transactionnDao.saveTransactionn(recipientTransaction); // Save the transaction

                            transaction.commit(); // Commit the transaction
                            System.out.println("Rs." + amount + " transferred successfully to account ID " + recipientAccountID);
                        } else {
                            System.out.println("Recipient account not found!");
                        }
                    } else {
                        System.out.println("Insufficient Balance!");
                    }
                } else {
                    System.out.println("Invalid Security Pin! Please try again.");
                }
            } else {
                System.out.println("Account not found!");
            }
        } catch (Exception e) {
            if (transaction != null) transaction.rollback(); // Rollback transaction in case of error
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close(); // Always close the session
            }
        }
    }

    public void getBalance(String email) {
        scanner.nextLine(); // Clear the newline character from the buffer
        System.out.print("Enter Security Pin: ");
        String enteredPin = scanner.nextLine().trim(); // Read and trim the security pin

        Session session = null;
        Transaction transaction = null;

        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction(); // Begin a new transaction

            // Retrieve the account using the email
            Account account = getAccountByEmail(email);
            if (account != null) {
                // Check if the provided security pin matches the stored pin
                String storedPin = account.getSecurityPin().trim(); // Trim any leading/trailing spaces
                if (storedPin.equals(enteredPin)) {
                    // Display the account balance
                    System.out.println("Current Balance: Rs." + account.getBalance());
                } else {
                    System.out.println("Invalid Security Pin! Please try again.");
                }
            } else {
                System.out.println("Account not found!");
            }
        } catch (Exception e) {
            if (transaction != null) transaction.rollback(); // Rollback transaction in case of error
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close(); // Always close the session
            }
        }
    }
}
