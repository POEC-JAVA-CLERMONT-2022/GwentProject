package com.example.gwent_projet.services.impl;

import com.example.gwent_projet.entity.card.CardDeck;
import com.example.gwent_projet.repository.CardDeckRepository;
import com.example.gwent_projet.services.dto.card.CardDTO;
import com.example.gwent_projet.services.dto.cardDeck.CardDeckDTO;
import com.example.gwent_projet.services.dto.cardDeck.CreateCardDeckDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.gwent_projet.services.CardDeckService;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
    public List<CardDeckDTO> getAllCardDecks() {
        ArrayList<CardDeckDTO> cardDeckDTOList = new ArrayList<>();
        for (CardDeck cardDeck : cardDeckRepository.findAll()) {
            CardDeckDTO cardDeckDTO = new CardDeckDTO();
            BeanUtils.copyProperties(cardDeck, cardDeckDTO);
            cardDeckDTOList.add(cardDeckDTO);
        }
        return cardDeckDTOList;
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
    public CardDeckDTO getCardDeckById(Long id) {

        CardDeck cardDeckSearch = cardDeckRepository.findById(id).orElse(null);

        CardDeckDTO searchResult = new CardDeckDTO(
                cardDeckSearch.getId(),
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