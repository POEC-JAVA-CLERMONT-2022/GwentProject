package com.example.gwent_projet.services.dto.deck;

import java.time.LocalDate;

import com.example.gwent_projet.services.dto.user.UserDeckDTO;

public class DeckDTO {

	public String name;
	public LocalDate createdAt;
	public UserDeckDTO owner;
	
	public DeckDTO(String name, LocalDate createdAt, UserDeckDTO owner) {
		this.name = name;
		this.createdAt = createdAt;
		this.owner = owner;
	}
	
	public DeckDTO() {
		
	}
	
	// overrides -----------------------------------------------------------------------------


	// getters & setters ----------------------------------------------------------------------

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

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
