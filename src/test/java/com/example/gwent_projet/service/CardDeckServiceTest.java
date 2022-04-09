package com.example.gwent_projet.service;

import com.example.gwent_projet.entity.Ability;
import com.example.gwent_projet.repository.CardDeckRepository;
import com.example.gwent_projet.services.CardDeckService;
import com.example.gwent_projet.services.dto.CardDTO;
import com.example.gwent_projet.services.dto.CardDeckDTO;
import com.example.gwent_projet.services.dto.CreateCardDTO;
import com.example.gwent_projet.services.dto.CreateCardDeckDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class CardDeckServiceTest {

    @Autowired
    private CardDeckService cardDeckService;
    @Autowired
    private CardDeckRepository cardDeckRepository;
    CreateCardDeckDTO createCardDeckDTO = new CreateCardDeckDTO("card deck");

    @Test
    @DisplayName("Test findAll Success")
    void testCardDeckFinfAll() {

        CardDeckDTO cardDeckDTO = cardDeckService.createCardDeck(createCardDeckDTO);

        List<CardDeckDTO> cardDeckDTOS = cardDeckService.getAllCardDecks();

        // test des valeurs
        for (CardDeckDTO cardDeckDTO1 : cardDeckDTOS) {
            Assertions.assertNotNull(cardDeckDTO1.getId(), "id");
            Assertions.assertNotNull(cardDeckDTO1.getName(), "Name");
            System.out.println(cardDeckDTO1.getId());
            System.out.println(cardDeckDTO1.getName());
        }
    }

    @Test
    @DisplayName("Test find by id Success")
    void testCardDeckFindById() {

        CardDeckDTO cardDeckDTO = cardDeckService.createCardDeck(createCardDeckDTO);

        CreateCardDeckDTO createCardDeckDTO = cardDeckService.getCardDeckById(1L);

        System.out.println(createCardDeckDTO.toString());
    }

    @Test
    @DisplayName("Test create Success")
    void testCardDeckCreation() {

        long nbCardDecks = this.cardDeckRepository.count();

        System.out.println("---------------------------------");
        System.out.println("Create :");

        CardDeckDTO cardDeckDTO = cardDeckService.createCardDeck(createCardDeckDTO);

        // assertions
        assertThat(cardDeckDTO).isNotNull();
        assertThat(cardDeckDTO.getId()).isNotNull();
        assertThat(cardDeckDTO.getName()).isEqualTo(createCardDeckDTO.getName());
        assertThat(this.cardDeckRepository.count()).isGreaterThan(nbCardDecks);

        System.out.println(cardDeckDTO.toString());
    }

    @Test
    @DisplayName("Test edit Success")
    void testCardDeckEdit() {

        System.out.println("---------------------------------");

        CardDeckDTO cardDeckDTO = cardDeckService.createCardDeck(createCardDeckDTO);
        System.out.println(cardDeckDTO.toString());

        Long id = cardDeckDTO.getId();
        System.out.println(id);

        // modifier
        CreateCardDeckDTO createCardDeckDTO = new CreateCardDeckDTO("neoMomo");

        //List<CardDTO> cardDTOS = cardService.getAllCards();
        this.cardDeckService.updateCardDeck(id, createCardDeckDTO);
        System.out.println(createCardDeckDTO.toString());
    }

    @Test
    @DisplayName("Test delete Success")
    void testCardDeckDelete() {

        System.out.println("---------------------------------");
        System.out.println("Delete by id :");

        CardDeckDTO cardDeckDTO = cardDeckService.createCardDeck(createCardDeckDTO);
        long nbCardDecks = this.cardDeckRepository.count();
        System.out.println(nbCardDecks);

        System.out.println(cardDeckDTO.toString());
        Long id = cardDeckDTO.getId();

        cardDeckService.deleteCardDeckById(id);
        System.out.println(nbCardDecks);
    }
}
