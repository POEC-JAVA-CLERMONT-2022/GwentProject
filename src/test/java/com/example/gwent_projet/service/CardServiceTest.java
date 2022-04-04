package com.example.gwent_projet.service;


import com.example.gwent_projet.entity.*;
import com.example.gwent_projet.repository.CardRepository;
import com.example.gwent_projet.services.CardService;
import com.example.gwent_projet.services.dto.CardDTO;
import com.example.gwent_projet.services.dto.CreateCardDTO;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.not;

@SpringBootTest
class CardApplicationTests {

    @Autowired
    private CardService cardService;
    @Autowired
    private CardRepository cardRepository;

    CreateCardDTO createCardDTO1 = new CreateCardDTO("name", "picture", 1, "description",
            "location", null, Ability.BERSERKER, Row.AGILE, Type.HERO);


    @Test
    @DisplayName("Test create Success")
    void testCardCreation() {

        long nbCards = this.cardRepository.count();

        System.out.println("---------------------------------");
        System.out.println("Create :");

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

        System.out.println(createdCard.toString());
    }


    @Test
    @DisplayName("Test edit Success")
    void testCardEdit() {

        System.out.println("---------------------------------");
        System.out.println("Get by id :");

        CreateCardDTO getByIdCard =  this.cardService.getCardById(1L);


        CreateCardDTO searchResult = new CreateCardDTO(
                getByIdCard.getName(),
                getByIdCard.getPicture(),
                getByIdCard.getPowerLvl(),
                getByIdCard.getDescription(),
                getByIdCard.getLocation(),
                getByIdCard.getCardDeck(),
                getByIdCard.getAbility(),
                getByIdCard.getRowName(),
                getByIdCard.getType() );

        System.out.println(searchResult.toString());

        ///////
        System.out.println("---------------------------------");
        System.out.println("Modify before :");

        //Carte a modifier
        //System.out.println(createdCard.toString());

        // carte modifier
        CreateCardDTO editCard = new CreateCardDTO("name", "picture", 1, "description",
                "location", null, Ability.BERSERKER, Row.AGILE, Type.HERO);


        //id de la carte a modifier
        //Long id = createdCard.getId();

        //Card cardToChange = cardRepository.getById(id);
        //BeanUtils.copyProperties(cardModify, createCardDTO1);

        ////CardDTO cardDTO = cardService.updateCard(id, editCard);

        System.out.println("---------------------------------");
        System.out.println("Modify after :");


    }


    @Test
    @DisplayName("Test delete Success")
    void testCardDelete() {

        System.out.println("---------------------------------");
        System.out.println("Delete by id :");

        cardService.deleteCardById(1L);
    }
}


    //Card card1 = new Card("Card Name", "picture", 2,
    //                "description", "location", new CardDeck("car deck"), Ability.BERSERKER, Row.AGILE, Type.HERO);




