package com.example.gwent_projet.entity.user;


import java.util.List;

import javax.persistence.*;

@Entity
@Table (name = "userDeck")
public class UserDeck {

	@Id
	@Column (name = "id", nullable = false)
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column (name = "name", length = 100, nullable = false)
	private String name;
	
	@JoinColumn (name = "user_id")
	@ManyToOne (fetch = FetchType.LAZY)
	private User owner;
	/*
	@Column (name = "cards")
	// @OneToMany (fetch = FetchType.LAZY, mappedBy = "card_id")
	private List<Card> cards;
	
	public UserDeck(String name, User owner, List<Card> cards) {
		this.name = name;
		this.owner = owner;
		this.cards = cards;
	}
	*/
	
	public UserDeck(String name, User owner) {
		this.name = name;
		this.owner = owner;
	}
	
	public UserDeck() {

	}
	
	// getters & setters ---------------------------

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}
}