package com.example.gwent_projet.service;

import com.example.gwent_projet.repository.CardDeckRepository;
import com.example.gwent_projet.services.CardDeckService;
import com.example.gwent_projet.services.dto.CardDeckDTO;
import com.example.gwent_projet.services.dto.CreateCardDeckDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class CardDeckServiceTests {

    @Autowired
    private CardDeckService cardDeckService;
    @Autowired
    private CardDeckRepository cardDeckRepository;

    CreateCardDeckDTO createCardDeckDTO = new CreateCardDeckDTO("card deck");

    @Test
    @DisplayName("Test findAll Success")
    void testCardDeckFindAll() {

        CardDeckDTO cardDeckDTO = cardDeckService.createCardDeck(createCardDeckDTO);

        List<CardDeckDTO> cardDeckDTOS = cardDeckService.getAllCardDecks();

        // test des valeurs
        for (CardDeckDTO cardDeckDTO1 : cardDeckDTOS) {
            Assertions.assertNotNull(cardDeckDTO1.getId(), "id");
            Assertions.assertNotNull(cardDeckDTO1.getName(), "Name");

            assertThat(cardDeckDTO1).isNotNull();
            assertThat(cardDeckDTO1.getId()).isNotNull();
            assertThat(cardDeckDTO1.getName()).isEqualTo(createCardDeckDTO.getName());
        }
    }

    @Test
    @DisplayName("Test find by id Success")
    void testCardDeckFindById() {

        CardDeckDTO cardDeckDTO = cardDeckService.createCardDeck(createCardDeckDTO);

        CreateCardDeckDTO createCardDeckDTO = cardDeckService.getCardDeckById(1L);

        // assertions
        assertThat(cardDeckDTO).isNotNull();
        assertThat(cardDeckDTO.getId()).isNotNull();
        assertThat(cardDeckDTO.getName()).isEqualTo(createCardDeckDTO.getName());
    }

    @Test
    @DisplayName("Test create Success")
    void testCardDeckCreation() {

        long nbCardDecks = this.cardDeckRepository.count();
        CardDeckDTO cardDeckDTO = cardDeckService.createCardDeck(createCardDeckDTO);

        // assertions
        assertThat(cardDeckDTO).isNotNull();
        assertThat(cardDeckDTO.getId()).isNotNull();
        assertThat(cardDeckDTO.getName()).isEqualTo(createCardDeckDTO.getName());
        assertThat(this.cardDeckRepository.count()).isGreaterThan(nbCardDecks);
    }

    @Test
    @DisplayName("Test edit Success")
    void testCardDeckEdit() {

        CardDeckDTO cardDeckDTO = cardDeckService.createCardDeck(createCardDeckDTO);
        Long id = cardDeckDTO.getId();

        // modifier
        CreateCardDeckDTO createCardDeckDTO = new CreateCardDeckDTO("neoMomo");

        //List<CardDTO> cardDTOS = cardService.getAllCards();
        this.cardDeckService.updateCardDeck(id, createCardDeckDTO);

    }

    @Test
    @DisplayName("Test delete Success")
    void testCardDeckDelete() {

        CardDeckDTO cardDeckDTO = cardDeckService.createCardDeck(createCardDeckDTO);
        long nbCardDecks = this.cardDeckRepository.count();
        System.out.println(nbCardDecks);

        System.out.println(cardDeckDTO.toString());
        Long id = cardDeckDTO.getId();

        cardDeckService.deleteCardDeckById(id);
        assertThat(this.cardDeckRepository.count()).isLessThan(nbCardDecks);
    }
}
