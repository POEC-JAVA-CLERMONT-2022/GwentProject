package com.example.gwent_projet.data;

import com.example.gwent_projet.entity.Card;

import java.util.ArrayList;

public interface DataProvider {

    public ArrayList<Card> getAll();

    public void add(Card card);

}
