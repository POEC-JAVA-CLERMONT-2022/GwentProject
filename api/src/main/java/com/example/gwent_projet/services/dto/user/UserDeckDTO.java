package com.example.gwent_projet.services.dto.user;

public class UserDeckDTO {
	
	public Long id;
	public String username;
	
	public UserDeckDTO(Long id, String username) {
		// TODO Auto-generated constructor stub
		this.id = id;
		this.username = username;
	}

	public UserDeckDTO() {

	}

	// overrides -----------------------------------------------------------------------------


	// getters & setters ----------------------------------------------------------------------

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
