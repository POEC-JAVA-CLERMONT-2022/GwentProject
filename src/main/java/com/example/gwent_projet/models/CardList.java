package com.example.gwent_projet.models;

public class CardList {

    private String cardlist;


    public CardList(String name) {
        this.cardlist = name;
    }

    @Override
    public String toString() {
        return "CardList{" +
                "cardlist='" + cardlist + '\'' +
                '}';
    }


    public String getCardlist() {
        return cardlist;
    }

}

