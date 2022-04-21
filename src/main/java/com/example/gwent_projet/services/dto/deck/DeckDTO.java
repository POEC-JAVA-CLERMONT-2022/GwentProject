package com.example.gwent_projet.services.dto.deck;

import java.time.LocalDate;

import com.example.gwent_projet.services.dto.user.UserDeckDTO;

public class DeckDTO extends DeckCreationDTO {

	public LocalDate createdAt;
	public UserDeckDTO owner;
	
	public DeckDTO(String name, LocalDate createdAt, UserDeckDTO owner) {
		// TODO Auto-generated constructor stub
		super();
		this.name = name;
		this.createdAt = createdAt;
		this.owner = owner;
	}
	
	public DeckDTO() {
		
	}
	
	// overrides -----------------------------------------------------------------------------


	// getters & setters ----------------------------------------------------------------------


	public UserDeckDTO getOwner() {
		return owner;
	}

	public void setOwner(UserDeckDTO owner) {
		this.owner = owner;
	}

	public LocalDate getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDate createdAt) {
		this.createdAt = createdAt;
	}
}
