package com.example.gwent_projet.services;

import com.example.gwent_projet.data.DataProvider;
import com.example.gwent_projet.data.InMemoryDataProvider;
import com.example.gwent_projet.models.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class CardService {


    private DataProvider dataProvider;

    public CardService() {
        dataProvider = new InMemoryDataProvider();
    }

    public ArrayList<Card> getAll() {
        return dataProvider.getAll();
    }


    public void add(Card card) {
        if(card == null || card.getName() == null) {
            throw new IllegalArgumentException("Card name is invalid");
        }
        dataProvider.add(card);
    }




    ////



    private Scanner scan;
    private List<Card> cards;


    /**
     *
     */
    public void runCard() {
        this.scan = new Scanner(System.in);
        this.cards = new LinkedList<>();


        CardList cardList = new CardList("ListeOfCards");
        Card card = new Card("Gerald", "geraldPic", 3, "Loup blanc",
                "Skelidge", Ability.COMMANDER, Row.CLOSE_COMBAT, Type.HERO);

        this.cards.add(card);

        System.out.println(cardList);
        //System.out.println(card);

        card.addCardlist(cardList);
        System.out.println(card);

        String answer;
        answer = this.scan.nextLine();

    }
}
