package com.example.gwent_projet.entity.pk;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import com.example.gwent_projet.entity.card.Card;
import com.example.gwent_projet.entity.user.User;

@Embeddable
public class UsersFavoritesPK implements Serializable {

    @Column(name = "userId")
    private User user;

    @Column(name = "cardId") // many to one
    private Card card;
}
