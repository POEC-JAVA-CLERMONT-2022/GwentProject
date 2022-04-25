package com.example.gwent_projet.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.gwent_projet.services.dto.card.CardDTO;

@Service
public interface DeckCardService {

	void addCardToDeck(Long deckId, Long cardId);
	
	List<CardDTO> getAllCardsInDeck(Long deckId);
	
	void deleteOneCardFromDeck(Long deckId, Long cardId);
	
	void deleteAllCardsFromDeck(Long deckId);

}
