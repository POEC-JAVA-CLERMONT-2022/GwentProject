package com.example.gwent_projet.service;


import com.example.gwent_projet.entity.*;
import com.example.gwent_projet.repository.CardRepository;
import com.example.gwent_projet.services.CardDeckService;
import com.example.gwent_projet.services.CardService;
import com.example.gwent_projet.services.dto.CardDTO;
import net.bytebuddy.implementation.bytecode.Throw;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;

@DisplayName("Test des cartes")
@SpringBootTest
@ExtendWith(MockitoExtension.class)
class CardServiceTest {

   /* @Mock private CardRepository cardRepository;

    @Autowired
    CardService cardService;
    @Autowired
    CardDeckService cardDeckService;



    @Test
    public void testCreateCard() {

        CardDeck deckBleu = new CardDeck("deckBleu");
        cardDeckService.saveCardDeck(deckBleu);
        CardDeck deckRouge = new CardDeck("deckRouge");
        cardDeckService.saveCardDeck(deckRouge);


        Card card = new Card("Card Name", "picture", 2,
                "description", "location", new CardDeck("deck noir") , Ability.BERSERKER, Row.AGILE, Type.HERO);

        Card card1 = new Card("Card Name", "picture", 2,
                "description", "location", deckRouge , Ability.BERSERKER, Row.AGILE, Type.HERO);

        cardService.saveCard(card);
        cardService.saveCard(card1);
        card.setName("Gerald");
        cardService.saveCard(card);

        Assertions.assertNotNull(card);
        Assertions.assertEquals("boris", card.getName());
    }

    /*@Test
    void testGetCards() {
        List<Card> cards = cardService.getAllCards();

        assertEquals(2, cards.size(), "La liste ne contient pas 2 photos");
    }*/

    /*@Test
    void testAddCard() {
        Card card = new Card("Card Name", "picture", 2,
                "description", "location", new CardDeck("car deck"), Ability.BERSERKER, Row.AGILE, Type.HERO);
        cardService.saveCard(card);

        List<Card> cards = cardService.getAllCards();
        assertEquals(3, cards.size(), "La liste ne contient pas 3 cartes");
    }

    @Test
    void testRemoveCard() {
        // prepare data
        Card card = new Card("Card Name", "picture", 2,
                "description", "location", new CardDeck("car deck"), Ability.BERSERKER, Row.AGILE, Type.HERO);
        // call method
        Long id = card.getId();
        cardService.deleteCardById(id);
    }*/


    //ard card1 = new Card("Card Name", "picture", 2,
    //                "description", "location", new CardDeck("car deck"), Ability.BERSERKER, Row.AGILE, Type.HERO);


}

