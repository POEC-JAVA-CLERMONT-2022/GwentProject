package com.example.gwent_projet.services.dto.deckCard;

import com.example.gwent_projet.services.dto.card.CardDTO;

public class DeckCardDTO {
	
	public CardDTO card;
	public int nbItems;
	
	public DeckCardDTO(CardDTO card, int nbItems) {
		this.card = card;
		this.nbItems = nbItems;
	}
	
	public DeckCardDTO() {
		
	}
	
	// overrides -----------------------------------------------------------------------------


	// getters & setters ----------------------------------------------------------------------

	public CardDTO getCard() {
		return card;
	}

	public void setCard(CardDTO card) {
		this.card = card;
	}

	public int getNbItems() {
		return nbItems;
	}

	public void setNbItems(int nbItems) {
		this.nbItems = nbItems;
	}

}
