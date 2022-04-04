package com.example.gwent_projet.services;

import com.example.gwent_projet.entity.*;
import com.example.gwent_projet.services.dto.CardDTO;
import com.example.gwent_projet.services.dto.CreateCardDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Validated
@Service
public interface CardService {

    @Transactional(readOnly = true)
    List<Card> getAllCards();

    @Transactional()
    CardDTO createCard(CreateCardDTO createCardDTO);

    @Transactional(readOnly = true)
    CreateCardDTO getCardById(Long id);

    CardDTO updateCard(Long id, CreateCardDTO editCard);

    void deleteCardById(Long id);

    List<Card> findCardsByCardDeck(Long id);
}

