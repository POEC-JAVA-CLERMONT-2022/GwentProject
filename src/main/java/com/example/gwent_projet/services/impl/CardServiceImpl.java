package com.example.gwent_projet.services.impl;

import com.example.gwent_projet.entity.Card;
import com.example.gwent_projet.repository.CardRepository;
import com.example.gwent_projet.services.CardService;
import com.example.gwent_projet.services.dto.CardDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class CardServiceImpl implements CardService {

    private CardRepository cardRepository;

    public CardServiceImpl(CardRepository cardRepository) {
        super();
        this.cardRepository = cardRepository;
    }

    @Override
    public List<Card> getAllCards() {
        return cardRepository.findAll();
    }



   /* @Override
    public Card testSaveCard(Card card) {
        return cardRepository.save(card);
    }*/
    @Override
    public CardDTO saveCard(Card createCard) {

        cardRepository.save(createCard);
        // DTO to be returned, populated with new card values
        CardDTO newCard = new CardDTO(
                createCard.getName(),
                createCard.getPicture(),
                createCard.getPowerLvl(),
                createCard.getDescription(),
                createCard.getLocation(),
                createCard.getCardDeck(),
                createCard.getAbility(),
                createCard.getRowName(),
                createCard.getType() );
        return newCard;
    }


    /*@Override
    public Card getCardById(Long id) {
        return cardRepository.findById(id).get();
    }*/
    @Override
    public CardDTO getCardById(Long id) {

        Card cardSearch = cardRepository.findById(id).orElse(null);

        CardDTO searchResult = new CardDTO(
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

    /*@Override
    public Card updateCard(Card card) {
        return cardRepository.save(card);
    }*/
    @Override
    public CardDTO updateCard(Long id, Card editCard) {

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
}
