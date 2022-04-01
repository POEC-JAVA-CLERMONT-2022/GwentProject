package com.example.gwent_projet.services;

import com.example.gwent_projet.entity.CardDeck;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface CardDeckService {

    List<CardDeck> getAllCardDecks();

    CardDeck saveCardDeck(CardDeck cardDeck);

    CardDeck getCardDeckById(Long id);

    CardDeck updateCardDeck(CardDeck cardDeck);

    void deleteCardDeckById(Long id);
}