package com.example.gwent_projet.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.gwent_projet.entity.deck.Deck;
import com.example.gwent_projet.services.dto.deck.DeckDTO;

@Service
public interface DeckService {

	DeckDTO createDeck(Deck createDeck);

	List<DeckDTO> getAllDecks();

	DeckDTO getDeckById(Long id);

	DeckDTO updateDeck(Long id, Deck deck);

	void deleteDeckById(Long id);

}
