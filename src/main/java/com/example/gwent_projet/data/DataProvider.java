package com.example.gwent_projet.data;

import java.util.ArrayList;

import com.example.gwent_projet.entity.Card;

public interface DataProvider {

    public ArrayList<Card> getAll();

    public void add(Card card);

}
