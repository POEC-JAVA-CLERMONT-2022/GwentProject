package com.example.gwent_projet.entity.deck;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.example.gwent_projet.entity.user.User;

@Entity
@Table (name = "deck")
public class Deck {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, updatable = false)
	private Long id;

	@JoinColumn(name = "user_id")
	@ManyToOne
	private User owner;

	@Column (name = "name", length = 100, nullable = false)
	private String name = "My card deck";

	@Column (name = "createdAt")
	private LocalDate createdAt;
	
	public Deck (User owner, String name, LocalDate createdAt) {
		this.owner = owner;
		this.name = name;
		this.createdAt = createdAt;
	}

	public Deck() {

	}

	// overrides -----------------------------------------------------------------------------


	// getters & setters ----------------------------------------------------------------------

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
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
}
