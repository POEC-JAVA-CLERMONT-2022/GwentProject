package com.example.gwent_projet.entity.user;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import com.example.gwent_projet.entity.pk.UsersFavoritesPK;

@Entity
@Table(name = "usersFavorites")
public class UsersFavorites implements Serializable {

    @EmbeddedId
    private UsersFavoritesPK id;

    @ManyToOne
    @MapsId("userId") // mapping the user ID from UsersFavoritesPK
    @JoinColumn(name = "userId")
    private Long userId;

    @ManyToOne
    @MapsId("cardId")
    @JoinColumn(name = "cardId")
    private Long cardId;

}