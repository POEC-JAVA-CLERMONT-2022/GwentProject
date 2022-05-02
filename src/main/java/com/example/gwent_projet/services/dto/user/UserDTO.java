package com.example.gwent_projet.services.dto.user;

public class UserDTO {

	public String username;
	public String email;

	public UserDTO(String username, String email) {
		// TODO Auto-generated constructor stub
		this.username = username;
		this.email = email;
	}

	public UserDTO() {

	}

	// overrides -----------------------------------------------------------------------------


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


}
