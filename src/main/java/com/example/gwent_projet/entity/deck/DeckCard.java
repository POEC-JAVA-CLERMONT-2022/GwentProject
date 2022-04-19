package com.example.gwent_projet.entity.deck;

import java.time.LocalDate;
import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table (name = "deckCard")
public class DeckCard {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, updatable = false)
	private Long id;
	
	@Column (name = "name", nullable = false)
	private Long owner; // contains ID of owner (user)

	@Column (name = "name", length = 100, nullable = false)
	private String name = "My card deck";

	@Column (name = "createdAt")
	private LocalDate createdAt;

	@JoinColumn(name = "cardId")
	@ManyToOne
	private ArrayList<Long> cards; // contains list of card IDs

	public DeckCard (String name, Long owner, LocalDate createdAt, ArrayList<Long> cards) {
		this.name = name;
		this.owner = owner;
		this.createdAt = createdAt;
		this.cards = cards;
	}

	public DeckCard() {

	}

	// overrides -----------------------------------------------------------------------------



	// getters & setters ----------------------------------------------------------------------

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getOwner() {
		return owner;
	}

	public void setOwner(Long owner) {
		this.owner = owner;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDate createdAt) {
		this.createdAt = createdAt;
	}

	public ArrayList<Long> getCards() {
		return cards;
	}

	public void setCards(ArrayList<Long> cards) {
		this.cards = cards;
	}


}
