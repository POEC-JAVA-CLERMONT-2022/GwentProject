package com.example.gwent_projet.services.dto.user;

public class UserCreationDTO extends UserDTO {
	
	public String password;
	
	public UserCreationDTO(String username, String email, String password) {
		// TODO Auto-generated constructor stub
		super();
		this.username = username;
		this.email = email;
		this.password = password;
	}

	public UserCreationDTO() {

	}

	// overrides -----------------------------------------------------------------------------


	// getters & setters ----------------------------------------------------------------------

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
