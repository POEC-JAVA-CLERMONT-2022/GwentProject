package com.example.gwent_projet.services.dto.deck;

import java.time.LocalDate;

public class DeckDTO {

	public String name;
	public LocalDate createdAt;
	public String owner;
	
	public DeckDTO(String name, LocalDate createdAt, String owner) {
		// TODO Auto-generated constructor stub
		this.name = name;
		this.createdAt = createdAt;
		this.owner = owner;
	}
	
}
