package com.example.gwent_projet.services.impl;

import com.example.gwent_projet.entity.Card;
import com.example.gwent_projet.repository.CardRepository;
import com.example.gwent_projet.services.CardService;
import com.example.gwent_projet.services.dto.CardDTO;
import com.example.gwent_projet.services.dto.CreateCardDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class CardServiceImpl implements CardService {

    @Autowired
    private CardRepository cardRepository;

    public CardServiceImpl(CardRepository cardRepository) {
        super();
        this.cardRepository = cardRepository;
    }

    @Override
    public List<CardDTO> getAllCards() {
        ArrayList<CardDTO> cardDTOList = new ArrayList<>();
        for (Card card : cardRepository.findAll()) {
            CardDTO cardDTO = new CardDTO();
            BeanUtils.copyProperties(card, cardDTO);
            cardDTOList.add(cardDTO);
        }
        return cardDTOList;
    }


    @Transactional()
    public CardDTO createCard(CreateCardDTO createCardDTO) {

        Card card = new Card(
                createCardDTO.getName(),
                createCardDTO.getPicture(),
                createCardDTO.getPowerLvl(),
                createCardDTO.getDescription(),
                createCardDTO.getLocation(),
                createCardDTO.getCardDeck(),
                createCardDTO.getAbility(),
                createCardDTO.getRowName(),
                createCardDTO.getType() );

        card = this.cardRepository.save(card);
        CardDTO cardDTO = new CardDTO();
        BeanUtils.copyProperties(card, cardDTO);

        return cardDTO;
    }


    @Override
    public CreateCardDTO getCardById(Long id) {

        Card cardSearch = cardRepository.findById(id).orElse(null);

        CreateCardDTO searchResult = new CreateCardDTO(
                cardSearch.getName(),
                cardSearch.getPicture(),
                cardSearch.getPowerLvl(),
                cardSearch.getDescription(),
                cardSearch.getLocation(),
                cardSearch.getCardDeck(),
                cardSearch.getAbility(),
                cardSearch.getRowName(),
                cardSearch.getType() );

        return searchResult;
    }


    @Override
    public CardDTO updateCard(Long id, CreateCardDTO editCard) {

        Card card = cardRepository.getById(id);

        BeanUtils.copyProperties(editCard, card);
        cardRepository.save(card);

        CardDTO cardDTO = new CardDTO();
        BeanUtils.copyProperties(card, cardDTO);

        return cardDTO;
    }


    @Override
    public void deleteCardById(Long id) {
        cardRepository.deleteById(id);
    }

    @Override
    public List<Card> findCardsByCardDeck(Long id) {

        List<Card> cards = new ArrayList<Card>(cardRepository.findByCardDeck_Id(id));

        return cards;
    }
}
