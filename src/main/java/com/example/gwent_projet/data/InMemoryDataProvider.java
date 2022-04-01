package com.example.gwent_projet.data;

import java.util.ArrayList;

import com.example.gwent_projet.entity.Card;

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
