package com.example.gwent_projet.entity.pk;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import com.example.gwent_projet.entity.card.Card;
import com.example.gwent_projet.entity.user.User;

@Embeddable
public class UserCardPK implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManyToOne(fetch = FetchType.LAZY)
	private User user;

	@ManyToOne(fetch = FetchType.LAZY)
	private Card card;

	protected UserCardPK() {

	}

	// overrides -----------------------------------------------------------------------------

	// getters & setters ---------------------------------------------------------------------

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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
