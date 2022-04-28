package com.example.gwent_projet.unit.service.card;
import com.example.gwent_projet.entity.card.Ability;
import com.example.gwent_projet.entity.card.Card;
import com.example.gwent_projet.entity.card.Row;
import com.example.gwent_projet.entity.card.Type;
import com.example.gwent_projet.repository.CardRepository;
import com.example.gwent_projet.services.dto.card.CardDTO;
import com.example.gwent_projet.services.impl.CardServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;

import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@SpringBootTest
public class CardMockServiceTest {

    @InjectMocks
    CardServiceImpl cardService;

    @Mock
    private CardRepository mockCardRepository;


    @Test
    public void getCards()
    {
        // data
        List<Card> cards = new ArrayList<>();

        cards.add(new Card("herve", "picture", 1, "description",
                "location", null, Ability.BERSERKER, Row.AGILE, Type.HERO));

        cards.add(new Card("mao", "picture1", 2, "description",
                "location", null, Ability.BERSERKER, Row.AGILE, Type.HERO));

        when(mockCardRepository.findAll()).thenReturn(cards);

        List<CardDTO> cardDTOS = cardService.getAllCards();
        assertEquals(cards.size(), cardDTOS.size());
        CardDTO cardDTO = cardDTOS.get(1);
        assertNotNull(cardDTO.getName());
    }
}
