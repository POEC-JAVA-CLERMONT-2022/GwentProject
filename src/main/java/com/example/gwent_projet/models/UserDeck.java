package com.example.gwent_projet.models;

import java.util.UUID;

import javax.persistence.*;

@Entity
@Table (name = "userDeck")
public class UserDeck {

	@Id
	@Column (name = "id", nullable = false)
	@GeneratedValue (strategy = GenerationType.AUTO)
	private UUID id;
	
	@Column (name = "name", length = 100, nullable = false)
	private String name;
	
	@JoinColumn (name = "user_id")
	@ManyToOne (fetch = FetchType.LAZY)
	private User owner;
	
	public UserDeck(String name, User owner) {
		this.name = name;
		this.owner = owner;
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