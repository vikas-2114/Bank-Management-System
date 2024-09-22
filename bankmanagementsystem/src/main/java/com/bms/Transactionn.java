package com.bms;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Transactionn") // Specify the table name if it differs from the class name
public class Transactionn {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int transactionID;

    private long accountNumber;
    private double amount;
    private String type; // Credit, Debit, or Transfer

    // Adding checkBalance as per previous discussions
    private double checkBalance;

    // Default constructor
    public Transactionn() {
    }

    // Constructor with parameters (optional)
    public Transactionn(long accountNumber, double amount, String type, double checkBalance) {
        this.accountNumber = accountNumber;
        this.amount = amount;
        this.type = type;
        this.checkBalance = checkBalance;
    }

    // Getters and Setters
    public int getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(int transactionID) {
        this.transactionID = transactionID;
    }

    public long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getCheckBalance() {
        return checkBalance;
    }

    public void setCheckBalance(double checkBalance) {
        this.checkBalance = checkBalance;
    }

    @Override
    public String toString() {
        return "Transactionn [transactionID=" + transactionID + ", accountNumber=" + accountNumber + ", amount=" + amount
                + ", type=" + type + ", checkBalance=" + checkBalance + "]";
    }
}
