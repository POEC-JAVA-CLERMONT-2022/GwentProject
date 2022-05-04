package com.example.gwent_projet.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.gwent_projet.services.dto.deck.DeckCardCreationDTO;
import com.example.gwent_projet.services.dto.deck.DeckCardDTO;
import com.example.gwent_projet.services.dto.deck.DeckCreationDTO;
import com.example.gwent_projet.services.dto.deck.DeckDTO;

@Service
public interface DeckService {

	DeckDTO createDeck(Long userId, DeckCreationDTO createDeck);

	List<DeckDTO> getAllDecks();

	DeckDTO getDeckById(Long id);

	DeckDTO updateDeck(Long id, DeckCreationDTO deck);

	void deleteDeckById(Long id);

	// -------------------------------------

	DeckCardCreationDTO addCardToDeck(Long deckId, Long cardId);

	List<DeckCardDTO> getAllCardsInDeck(Long deckId);

	void deleteOneCardFromDeck(Long deckId, Long cardId);

	void deleteAllCardsFromDeck(Long deckId);

}
