package com.example.gwent_projet.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.gwent_projet.services.dto.deckCard.DeckCardCreationDTO;
import com.example.gwent_projet.services.dto.deckCard.DeckCardDTO;

@Service
public interface DeckCardService {

	DeckCardCreationDTO addCardToDeck(Long deckId, Long cardId);
	
	List<DeckCardDTO> getAllCardsInDeck(Long deckId);
	
	void deleteOneCardFromDeck(Long deckId, Long cardId);
	
	void deleteAllCardsFromDeck(Long deckId);

}
