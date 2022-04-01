package com.example.gwent_projet.service;


import com.example.gwent_projet.entity.*;
import com.example.gwent_projet.repository.CardRepository;
import com.example.gwent_projet.services.CardService;
import com.example.gwent_projet.services.dto.CardDTO;
import com.example.gwent_projet.services.dto.CreateCardDTO;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class CardApplicationTests {

    @Autowired
    private CardService cardService;
    @Autowired
    private CardRepository cardRepository;

    @Test
    @DisplayName("Test create Success")
    void testCardCreation() {

        EasyRandom easyRandom = new EasyRandom();
        long nbCards = this.cardRepository.count();

        CreateCardDTO createCardDTO = easyRandom.nextObject(CreateCardDTO.class);

        CardDTO createdCard = cardService.saveCard(createCardDTO);

        // assertions
        assertThat(createdCard).isNotNull();
        assertThat(createdCard.getId()).isNotNull();
        assertThat(createdCard.getName()).isEqualTo(createCardDTO.getName());
        assertThat(this.cardRepository.count()).isGreaterThan(nbCards);
        assertThat(this.cardRepository.getById(createdCard.getId())).isNotNull();


    }

}


    //Card card1 = new Card("Card Name", "picture", 2,
    //                "description", "location", new CardDeck("car deck"), Ability.BERSERKER, Row.AGILE, Type.HERO);




