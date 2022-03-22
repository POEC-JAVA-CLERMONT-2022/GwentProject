package com.example.gwent_projet.models;

public class Admin {

	private String username;
	private String email;
	private String password;
	
	public Admin (String username, String email, String password) {
		this.username = username;
		this.email = email;
		this.password = password;
	}
	
	// getters & setters ----------------------------------------------------------------------
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
