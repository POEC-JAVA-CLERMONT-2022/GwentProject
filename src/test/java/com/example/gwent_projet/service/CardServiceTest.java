package com.example.gwent_projet.service;


import com.example.gwent_projet.entity.*;
import com.example.gwent_projet.services.CardService;
import com.example.gwent_projet.services.dto.CardDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class CardServiceTest {

    @Autowired
    CardService cardService;

    @Test
    void simpleTest() {
        //System.out.println("simpleTest");
        //Assertions.assertTrue(true);

        CardDTO card = new CardDTO("name", "picture", 1, "description", "location",
                new CardDeck("Carddeck"), Ability.BERSERKER, Row.SIEGE, Type.HERO);


        //
        // card = this.cardService.saveCard(card);

    }

}
