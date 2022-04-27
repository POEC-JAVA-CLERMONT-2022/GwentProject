package com.example.gwent_projet.services.dto.favorites;

import com.example.gwent_projet.services.dto.card.CardDTO;
import com.example.gwent_projet.services.dto.user.UserDTO;

public class FavoritesDTO {
	
	public UserDTO user;
	public CardDTO card;
	
	public FavoritesDTO(UserDTO user, CardDTO card) {
		this.user = user;
		this.card = card;
	}
	
	public FavoritesDTO() {
		
	}
	
	// overrides -----------------------------------------------------------------------------


	// getters & setters ----------------------------------------------------------------------

	public UserDTO getUser() {
		return user;
	}

	public void setUser(UserDTO user) {
		this.user = user;
	}

	public CardDTO getCard() {
		return card;
	}

	public void setCard(CardDTO card) {
		this.card = card;
	}
}
