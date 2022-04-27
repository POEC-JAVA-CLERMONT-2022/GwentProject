package com.example.gwent_projet.services;

import com.example.gwent_projet.services.dto.cardDeck.CardDeckDTO;
import com.example.gwent_projet.services.dto.cardDeck.CreateCardDeckDTO;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface CardDeckService {

    List<CardDeckDTO> getAllCardDecks();

    CardDeckDTO createCardDeck(CreateCardDeckDTO createCardDeckDTO);

    CardDeckDTO getCardDeckById(Long id);

    CardDeckDTO updateCardDeck(Long id, CreateCardDeckDTO createCardDeckDTO);

    void deleteCardDeckById(Long id);
}