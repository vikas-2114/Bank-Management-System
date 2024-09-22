package com.bms;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountID;
    private long accountNumber;
    private String name;
    private String email;
    private int password;
    private Long contactNo;
    private String address;
    private String dateOfBirth;
    private String accountType;
    private Long aadharNo;
    private String pancardNo;
    private String securityPin; // Add this field
    private double balance;     // Add this field

    // Constructors
    public Account(Long accountID, Long accountNumber, String name, String email, int password, Long contactNo, String address,
                   String dateOfBirth, String accountType, Long aadharNo, String pancardNo, String securityPin, double balance) {
        this.accountID = accountID;
        this.accountNumber = accountNumber;
        this.name = name;
        this.email = email;
        this.password = password;
        this.contactNo = contactNo;
        this.address = address;
        this.dateOfBirth = dateOfBirth;
        this.accountType = accountType;
        this.aadharNo = aadharNo;
        this.pancardNo = pancardNo;
        this.securityPin = securityPin;
        this.balance = balance;
    }

    public Account() {
    }

    // Getters and Setters
    public Long getAccountID() {
        return accountID;
    }

    public void setAccountID(long newAccountNumber) {
        this.accountID = (Long) newAccountNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPassword() {
        return password;
    }

    public void setPassword(int password) {
        this.password = password;
    }

    public Long getContactNo() {
        return contactNo;
    }

    public void setContactNo(Long contactNo) {
        this.contactNo = contactNo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public Long getAadharNo() {
        return aadharNo;
    }

    public void setAadharNo(Long aadharNo) {
        this.aadharNo = aadharNo;
    }

    public String getPancardNo() {
        return pancardNo;
    }

    public void setPancardNo(String pancardNo) {
        this.pancardNo = pancardNo;
    }

    public String getSecurityPin() {
        return securityPin;
    }

    public void setSecurityPin(String securityPin) {
        this.securityPin = securityPin;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Account [accountID=" + accountID + ", accountNumber =" + accountNumber +", name=" + name + ", email=" + email + ", password=" + password
                + ", contactNo=" + contactNo + ", address=" + address + ", dateOfBirth=" + dateOfBirth
                + ", accountType=" + accountType + ", aadharNo=" + aadharNo + ", pancardNo=" + pancardNo
                + ", securityPin=" + securityPin + ", balance=" + balance + "]";
    }

	public long getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(long accountNumber) {
		this.accountNumber = accountNumber;
	}
}
