package com.example.gwent_projet.services;

import com.example.gwent_projet.entity.CardDeck;
import com.example.gwent_projet.services.dto.CardDTO;
import com.example.gwent_projet.services.dto.CardDeckDTO;
import com.example.gwent_projet.services.dto.CreateCardDTO;
import com.example.gwent_projet.services.dto.CreateCardDeckDTO;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface CardDeckService {

    List<CardDeck> getAllCardDecks();

    CardDeckDTO createCardDeck(CreateCardDeckDTO createCardDeckDTO);

    CreateCardDeckDTO getCardDeckById(Long id);

    CardDeckDTO updateCardDeck(Long id, CreateCardDeckDTO createCardDeckDTO);

    void deleteCardDeckById(Long id);
}