package com.example.gwent_projet.service;


import com.example.gwent_projet.entity.*;
import com.example.gwent_projet.services.CardService;
import com.example.gwent_projet.services.dto.CardDTO;
import org.junit.jupiter.api.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

@DisplayName("Test des cartes")
public class CardServiceTest {

    @Autowired
    CardService cardService;

    @BeforeAll
    static void setup() {
        System.out.println("@BeforeAll - executes once before all test methods in this class");
    }

    @BeforeEach
    void init() {
        System.out.println("@BeforeEach - executes before each test method in this class");
    }

    @Test
    void simpleTest() {
        //System.out.println("simpleTest");
        //Assertions.assertTrue(true);

        CardDTO cardDTO = new CardDTO("name", "picture", 1, "description", "location",
                new CardDeck("Carddeck"), Ability.BERSERKER, Row.SIEGE, Type.HERO);


        Card card = new Card();

        BeanUtils.copyProperties(cardDTO, card);

        //cardDTO = this.cardService.saveCard(card);

        Assertions.assertEquals(card, cardDTO, "Dimensions non egales");

        System.out.println(card.toString());

    }

}
