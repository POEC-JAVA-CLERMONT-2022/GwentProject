package com.example.gwent_projet.services.dto.user;

// ORM
public class UserDTO {

	// public int role;
	public String username;
	public String email;
	
	public UserDTO(String username, String email) {
		// TODO Auto-generated constructor stub
		this.username = username;
		this.email = email;
	}
}
