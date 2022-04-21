package com.example.gwent_projet.entity.pk;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import com.example.gwent_projet.entity.card.Card;
import com.example.gwent_projet.entity.deck.Deck;

@Embeddable
public class DeckCardPK implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManyToOne(fetch = FetchType.LAZY)
	private Deck deck;

	@ManyToOne(fetch = FetchType.LAZY)
	private Card card;

	protected DeckCardPK() {

	}

	// overrides -----------------------------------------------------------------------------

	// getters & setters ---------------------------------------------------------------------

	public Deck getDeck() {
		return deck;
	}

	public void setDeck(Deck deck) {
		this.deck = deck;
	}

	public Card getCard() {
		return card;
	}

	public void setCard(Card card) {
		this.card = card;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
