package com.example.gwent_projet.models;

import java.util.LinkedList;
import java.util.List;

public class Card {

    private String name;
    private String picture;
    private Integer powerLvl;
    private String description;
    private String location;

    private List<CardList> cardsLists;

    private Ability ability;
    private Row row;
    private Type type;




    public Card(String name, String picture, Integer powerLvl, String description,
                String location, Ability ability, Row row, Type type) {
        this.name = name;
        this.picture = picture;
        this.powerLvl = powerLvl;
        this.description = description;
        this.location = location;
        this.ability = ability;
        this.row = row;
        this.type = type;
        this.cardsLists = new LinkedList<CardList>();

    }

    @Override
    public String toString() {
        return "Card{" +
                "name='" + name + '\'' +
                ", picture='" + picture + '\'' +
                ", powerLvl=" + powerLvl +
                ", description='" + description + '\'' +
                ", location='" + location + '\'' +
                ", ability='" +  ability.getShortName() + '\''+
                ", row='" + row.getRowName() + '\''+
                ", type='" + type.getTypeName() + '\''+
                ", loans=" + cardsLists +
                '}';
    }




    //getter setter
    public String getName() {
        return name;
    }

    public String getPicture() {
        return picture;
    }

    public Integer getPowerLvl() {
        return powerLvl;
    }

    public String getDescription() {
        return description;
    }

    public String getLocation() {
        return location;
    }

    public List<CardList> getCardsList() {
        return cardsLists;
    }

    public void addCardlist(CardList cardList) {
        this.cardsLists.add(cardList);
    }


}
