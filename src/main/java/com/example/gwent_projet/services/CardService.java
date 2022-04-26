package com.example.gwent_projet.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import com.example.gwent_projet.entity.card.Card;
import com.example.gwent_projet.services.dto.card.CardDTO;
import com.example.gwent_projet.services.dto.card.CreateCardDTO;

@Validated
@Service
public interface CardService {

    @Transactional(readOnly = true)
    List<CardDTO> getAllCards();

    @Transactional()
    CardDTO createCard(CreateCardDTO createCardDTO);

    @Transactional(readOnly = true)
    CreateCardDTO getCardById(Long id);

    CardDTO updateCard(Long id, CreateCardDTO editCard);

    void deleteCardById(Long id);

    @Transactional(readOnly = true)
    List<Card> findCardsByCardDeck(Long id);

    @Transactional(readOnly = true)
    List<Card> findCardsByName(String name);
}

