package com.example.gwent_projet.entity.deck;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import com.example.gwent_projet.entity.pk.DeckCardPK;

@Entity
@AssociationOverrides(
		{
			@AssociationOverride(name = "deckCard.deck", joinColumns = @JoinColumn(name = "deck_id")),
			@AssociationOverride(name = "deckCard.card", joinColumns = @JoinColumn(name = "card_id")),
		}
		)
@Table (name = "deckCard")
public class DeckCard {

	@EmbeddedId
	private DeckCardPK id;

	public DeckCard(DeckCardPK id) {
		this.id = id;
	}

	public DeckCard() {
	}

	// overrides -----------------------------------------------------------------------------


	// getters & setters ----------------------------------------------------------------------

	public DeckCardPK getId() {
		return id;
	}

	public void setId(DeckCardPK id) {
		this.id = id;
	}
}
