package com.example.gwent_projet.services.impl;

import com.example.gwent_projet.entity.Card;
import com.example.gwent_projet.entity.CardDeck;
import com.example.gwent_projet.repository.CardDeckRepository;
import com.example.gwent_projet.repository.CardRepository;
import com.example.gwent_projet.services.dto.CardDTO;
import com.example.gwent_projet.services.dto.CardDeckDTO;
import com.example.gwent_projet.services.dto.CreateCardDTO;
import com.example.gwent_projet.services.dto.CreateCardDeckDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.gwent_projet.services.CardDeckService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CardDeckServiceImpl implements CardDeckService {

    @Autowired
    private CardDeckRepository cardDeckRepository;

    public CardDeckServiceImpl(CardDeckRepository cardDeckRepository) {
        super();
        this.cardDeckRepository = cardDeckRepository;
    }


    @Override
    public List<CardDeck> getAllCardDecks() {
        return cardDeckRepository.findAll();
    }

    @Transactional()
    public CardDeckDTO createCardDeck(CreateCardDeckDTO createCardDeckDTO) {

        CardDeck cardDeck = new CardDeck(
                createCardDeckDTO.getName() );

        cardDeck = this.cardDeckRepository.save(cardDeck);

        CardDeckDTO cardDeckDTO = new CardDeckDTO();
        BeanUtils.copyProperties(cardDeck, cardDeckDTO);

        return cardDeckDTO;
    }

    @Override
    public CreateCardDeckDTO getCardDeckById(Long id) {

        CardDeck cardDeckSearch = cardDeckRepository.findById(id).orElse(null);

        CreateCardDeckDTO searchResult = new CreateCardDeckDTO(
                cardDeckSearch.getName() );

        return searchResult;
    }

    @Override
    public CardDeckDTO updateCardDeck(Long id, CreateCardDeckDTO editCardDeckDTO) {

        CardDeck cardDeck = cardDeckRepository.getById(id);

        BeanUtils.copyProperties(editCardDeckDTO, cardDeck);
        cardDeckRepository.save(cardDeck);

        CardDeckDTO cardDeckDTO = new CardDeckDTO();
        BeanUtils.copyProperties(cardDeck, cardDeckDTO);

        return cardDeckDTO;
    }

    @Override
    public void deleteCardDeckById(Long id) {
        cardDeckRepository.deleteById(id);
    }
}