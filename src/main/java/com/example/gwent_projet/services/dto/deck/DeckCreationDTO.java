package com.example.gwent_projet.services.dto.deck;

import java.time.LocalDate;

public class DeckCreationDTO {
	
	public String name;
	
	public DeckCreationDTO(String name) {
		// TODO Auto-generated constructor stub
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
