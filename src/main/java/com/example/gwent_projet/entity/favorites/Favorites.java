package com.example.gwent_projet.entity.favorites;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import com.example.gwent_projet.entity.pk.UserCardPK;

@Entity
@AssociationOverrides(
		{
			@AssociationOverride(name = "favorites.user", joinColumns = @JoinColumn(name = "user_id")),
			@AssociationOverride(name = "favorites.card", joinColumns = @JoinColumn(name = "card_id")),
		}
		)
@Table(name = "favorites")
public class Favorites {

	@EmbeddedId
	private UserCardPK id;

	public Favorites(UserCardPK id) {
		this.id = id;
	}

	public Favorites() {
	}

	// overrides -----------------------------------------------------------------------------


	// getters & setters ----------------------------------------------------------------------


	public UserCardPK getId() {
		return id;
	}

	public void setId(UserCardPK id) {
		this.id = id;
	}



}