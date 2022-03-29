package com.example.gwent_projet.services.impl;

import com.example.gwent_projet.entity.CardDeck;
import com.example.gwent_projet.repository.CardDeckRepository;
import org.springframework.stereotype.Service;
import com.example.gwent_projet.services.CardDeckService;

import java.util.List;

@Service
public class CardDeckServiceImpl implements CardDeckService {

    private CardDeckRepository cardDeckRepository;

    public CardDeckServiceImpl(CardDeckRepository cardDeckRepository) {
        super();
        this.cardDeckRepository = cardDeckRepository;
    }


    @Override
    public List<CardDeck> getAllCardDecks() {
        return cardDeckRepository.findAll();
    }

    @Override
    public CardDeck saveCardDeck(CardDeck cardDeck) {
        return null;
    }

    @Override
    public CardDeck getCardDeckById(Long id) {
        return null;
    }

    @Override
    public CardDeck updateCardDeck(CardDeck cardDeck) {
        return null;
    }

    @Override
    public void deleteCardDeckById(Long id) {

    }
}