package com.example.gwent_projet.services.dto.card;

import com.example.gwent_projet.entity.card.Ability;
import com.example.gwent_projet.entity.card.CardDeck;
import com.example.gwent_projet.entity.card.Row;
import com.example.gwent_projet.entity.card.Type;

public class CreateCardDTO {


    private String name;

    private String picture;

    private Integer powerLvl;

    private String description;

    private String location;

    private CardDeck cardDeck;

    private Ability ability;

    private Row rowName;

    private Type type;

    public CreateCardDTO(String name, String picture, Integer powerLvl, String description,
                         String location, CardDeck cardDeck, Ability ability, Row rowName, Type type) {
        this.name = name;
        this.picture = picture;
        this.powerLvl = powerLvl;
        this.description = description;
        this.location = location;
        this.cardDeck = cardDeck;
        this.ability = ability;
        this.rowName = rowName;
        this.type = type;
    }

    public CreateCardDTO() {

    }

    @Override
    public String toString() {
        return "CreateCardDTO{" +
                "name='" + name + '\'' +
                ", picture='" + picture + '\'' +
                ", powerLvl=" + powerLvl +
                ", description='" + description + '\'' +
                ", location='" + location + '\'' +
                ", cardDeck=" + cardDeck +
                ", ability=" + ability +
                ", rowName=" + rowName +
                ", type=" + type +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Integer getPowerLvl() {
        return powerLvl;
    }

    public void setPowerLvl(Integer powerLvl) {
        this.powerLvl = powerLvl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public CardDeck getCardDeck() {
        return cardDeck;
    }

    public void setCardDeck(CardDeck cardDeck) {
        this.cardDeck = cardDeck;
    }

    public Ability getAbility() {
        return ability;
    }

    public void setAbility(Ability ability) {
        this.ability = ability;
    }

    public Row getRowName() {
        return rowName;
    }

    public void setRowName(Row rowName) {
        this.rowName = rowName;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}