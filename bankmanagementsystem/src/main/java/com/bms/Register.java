package com.bms;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Register {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int usernameID;
    private String email;
    private int password;
    private Long contactNo;
    
    
	public Register(int usernameID, String email, int password, Long contactNo) {
		super();
		this.usernameID = usernameID;
		this.email = email;
		this.password = password;
		this.contactNo = contactNo;
	}


	public Register() {
		super();
	}


	public int getUsernameID() {
		return usernameID;
	}


	public void setUsernameID(int usernameID) {
		this.usernameID = usernameID;
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
	
	@Override
	public String toString() {
		return "Register [usernameID=" + usernameID + ", email=" + email + ", password=" + password
				+ ", contactNo=" + contactNo + "]";
	}

}
