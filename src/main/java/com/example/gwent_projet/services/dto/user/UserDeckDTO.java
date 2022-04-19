package com.example.gwent_projet.services.dto.user;

import java.util.List;

import com.example.gwent_projet.entity.Card;

public class UserDeckDTO {

	public String name;
	public String owner;
	public List<Card> cards;
	
	public UserDeckDTO(String name, String owner, List<Card> cards) {
		// TODO Auto-generated constructor stub
		this.name = name;
		this.owner = owner;
		this.cards = cards;
	}
	
	
}
