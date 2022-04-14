package com.example.gwent_projet.service;


import com.example.gwent_projet.entity.*;
import com.example.gwent_projet.repository.CardDeckRepository;
import com.example.gwent_projet.repository.CardRepository;
import com.example.gwent_projet.services.CardDeckService;
import com.example.gwent_projet.services.CardService;
import com.example.gwent_projet.services.dto.CardDTO;
import com.example.gwent_projet.services.dto.CardDeckDTO;
import com.example.gwent_projet.services.dto.CreateCardDTO;
import com.example.gwent_projet.services.dto.CreateCardDeckDTO;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.not;

@SpringBootTest
@Transactional()
class CardApplicationTests {

    @Autowired
    private CardService cardService;
    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private CardDeckService cardDeckService;
    @Autowired
    private CardDeckRepository cardDeckRepository;
    CreateCardDeckDTO createCardDeckDTO = new CreateCardDeckDTO("card deck");

    CreateCardDTO createCardDTO1 = new CreateCardDTO("name", "picture", 1, "description",
            "location", null, Ability.BERSERKER, Row.AGILE, Type.HERO);


    @Test
    @DisplayName("Test findAll Success")
    void testCardFindAll() {

        CardDTO createdCard = cardService.createCard(createCardDTO1);
        CardDTO createdCard1 = cardService.createCard(createCardDTO1);
        List<CardDTO> cardDTOCard = cardService.getAllCards();

        // test des valeurs
        for (CardDTO card : cardDTOCard) {
            // assertions
            Assertions.assertNotNull(card.getId(), "id");
            Assertions.assertNotNull(card.getName(), "Name");

            assertThat(card).isNotNull();
            assertThat(card.getId()).isNotNull();
            assertThat(card.getName()).isEqualTo(createCardDTO1.getName());
            assertThat(card.getPicture()).isEqualTo(createCardDTO1.getPicture());
            assertThat(card.getPowerLvl()).isEqualTo(createCardDTO1.getPowerLvl());
            assertThat(card.getDescription()).isEqualTo(createCardDTO1.getDescription());
            assertThat(card.getLocation()).isEqualTo(createCardDTO1.getLocation());
            assertThat(card.getAbility()).isEqualTo(createCardDTO1.getAbility());
            assertThat(card.getRowName()).isEqualTo(createCardDTO1.getRowName());
            assertThat(card.getType()).isEqualTo(createCardDTO1.getType());
        }
    }

    @Test
    @DisplayName("Test find by id Success")
    void testCardFindById() {

        CardDTO createdCard = cardService.createCard(createCardDTO1);
        CreateCardDTO card = cardService.getCardById(1L);

        // assertions
        assertThat(createdCard).isNotNull();
        assertThat(createdCard.getId()).isNotNull();
        assertThat(createdCard.getName()).isEqualTo(createCardDTO1.getName());
        assertThat(createdCard.getPicture()).isEqualTo(createCardDTO1.getPicture());
        assertThat(createdCard.getPowerLvl()).isEqualTo(createCardDTO1.getPowerLvl());
        assertThat(createdCard.getDescription()).isEqualTo(createCardDTO1.getDescription());
        assertThat(createdCard.getLocation()).isEqualTo(createCardDTO1.getLocation());
        assertThat(createdCard.getAbility()).isEqualTo(createCardDTO1.getAbility());
        assertThat(createdCard.getRowName()).isEqualTo(createCardDTO1.getRowName());
        assertThat(createdCard.getType()).isEqualTo(createCardDTO1.getType());
        assertThat(this.cardRepository.getById(createdCard.getId())).isNotNull();
    }


    @Test
    @DisplayName("Test create Success")
    void testCardCreation() {

        long nbCards = this.cardRepository.count();

        System.out.println("---------------------------------");
        System.out.println("Create card :");

        CardDTO createdCard = cardService.createCard(createCardDTO1);

        // assertions
        assertThat(createdCard).isNotNull();
        assertThat(createdCard.getId()).isNotNull();
        assertThat(createdCard.getName()).isEqualTo(createCardDTO1.getName());
        assertThat(createdCard.getPicture()).isEqualTo(createCardDTO1.getPicture());
        assertThat(createdCard.getPowerLvl()).isEqualTo(createCardDTO1.getPowerLvl());
        assertThat(createdCard.getDescription()).isEqualTo(createCardDTO1.getDescription());
        assertThat(createdCard.getLocation()).isEqualTo(createCardDTO1.getLocation());
        assertThat(createdCard.getAbility()).isEqualTo(createCardDTO1.getAbility());
        assertThat(createdCard.getRowName()).isEqualTo(createCardDTO1.getRowName());
        assertThat(createdCard.getType()).isEqualTo(createCardDTO1.getType());
        assertThat(this.cardRepository.count()).isGreaterThan(nbCards);
        assertThat(this.cardRepository.getById(createdCard.getId())).isNotNull();
    }


    @Test
    @DisplayName("Test edit Success")
    void testCardEdit() {
        System.out.println("---------------------------------");

        CardDTO createdCard = cardService.createCard(createCardDTO1);
        Long id = createdCard.getId();
        System.out.println(createdCard.toString());

        // modifier
       CreateCardDTO cardUpdate = new CreateCardDTO(
               "Momo", "Mama", 2, "description",
               "location", null, Ability.BERSERKER, Row.AGILE, Type.HERO);


        List<CardDTO> cardDTOS = cardService.getAllCards();
        this.cardService.updateCard(id, cardUpdate);
    }


    @Test
    @DisplayName("Test delete Success")
    void testCardDelete() {

        System.out.println("---------------------------------");
        System.out.println("Delete by id :");

        //Création et verif en BDD
        CardDTO createdCard = cardService.createCard(createCardDTO1);
        long nbCards = this.cardRepository.count();
        Long id = createdCard.getId();

        //delete et verif
        cardService.deleteCardById(id);
        long nbCardsAfter = this.cardRepository.count();
        assertThat(this.cardRepository.count()).isLessThan(nbCards);
    }

    @Test
    @DisplayName("Test get card by deck")
    void testGetCardByDeck() {

        System.out.println("---------------------------------");
        System.out.println("get card by deck id :");

        //création de cardDeck
        CardDeckDTO createCardDeck = cardDeckService.createCardDeck(createCardDeckDTO);
        Long deckId = createCardDeck.getId();
        CardDeck cardDeck = new CardDeck();
        BeanUtils.copyProperties(createCardDeck, cardDeck);

        //création de card
        CreateCardDTO createCardDTO = new CreateCardDTO("carte name", "picture", 1, "description",
                "location", cardDeck, Ability.BERSERKER, Row.AGILE, Type.HERO);
        CreateCardDTO createCardDTO1 = new CreateCardDTO("carte name 1", "picture", 1, "description",
                "location", cardDeck, Ability.BERSERKER, Row.AGILE, Type.HERO);
        CreateCardDTO createCardDTO2 = new CreateCardDTO("carte name 2", "picture", 1, "description",
                "location", cardDeck, Ability.BERSERKER, Row.AGILE, Type.HERO);

        CardDTO createdCard = cardService.createCard(createCardDTO);
        CardDTO createdCard1 = cardService.createCard(createCardDTO1);
        CardDTO createdCard2 = cardService.createCard(createCardDTO2);


        // lister toutes les cartes
        List<Card> cards = new ArrayList<>(cardService.findCardsByCardDeck(deckId));

        //System.out.println(cards);
        for (Card card : cards) {
            Assertions.assertNotNull(card.getId(), "id");
            Assertions.assertNotNull(card.getName(), "Name");

            assertThat(card).isNotNull();
            assertThat(card.getId()).isNotNull();
        }
    }
}


    //Card card1 = new Card("Card Name", "picture", 2,
    //                "description", "location", new CardDeck("car deck"), Ability.BERSERKER, Row.AGILE, Type.HERO);




