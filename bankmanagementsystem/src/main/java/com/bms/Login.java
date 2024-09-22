package com.bms;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Login {
	 @Id
	private String email;
    private int password;
    
    
	public Login(String email, int password) {
		super();
		this.email = email;
		this.password = password;
		
		
	}

	public Login() {
		super();
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
	
	@Override
	public String toString() {
		return "Login [email=" + email + ", password=" + password
				+"]";
	}

}
