package com.example.gwent_projet.services.dto.deck;

public class DeckCardCreationDTO {
	
	public Long cardId;
	public Long deckId;
	public int nbItems;
	
	public DeckCardCreationDTO(Long cardId, Long deckId, int nbItems) {
		this.cardId = cardId;
		this.deckId = deckId;
		this.nbItems = nbItems;
	}
	
	public DeckCardCreationDTO() {
		
	}
	
	// overrides -----------------------------------------------------------------------------


	// getters & setters ----------------------------------------------------------------------

	public Long getCardId() {
		return cardId;
	}

	public void setCardId(Long cardId) {
		this.cardId = cardId;
	}

	public Long getDeckId() {
		return deckId;
	}

	public void setDeckId(Long deckId) {
		this.deckId = deckId;
	}

	public int getNbItems() {
		return nbItems;
	}

	public void setNbItems(int nbItems) {
		this.nbItems = nbItems;
	}
}
