package com.example.gwent_projet.services.dto.deck;

public class DeckCreationDTO {
	
	public String name;
	
	public DeckCreationDTO(String name) {
		this.name = name;
	}
	
	public DeckCreationDTO() {
		
	}
	
	// overrides -----------------------------------------------------------------------------


	// getters & setters ----------------------------------------------------------------------

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
