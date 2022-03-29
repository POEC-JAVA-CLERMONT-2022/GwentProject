package com.example.gwent_projet.data;

import com.example.gwent_projet.entity.Card;

import java.util.ArrayList;

public class InMemoryDataProvider implements DataProvider {

    private ArrayList<Card> cards = new ArrayList<Card>();

    @Override
    public ArrayList<Card> getAll() {
        return cards;
    }


    @Override
    public void add(Card card) {
        cards.add(card);
    }

}
