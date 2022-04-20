package com.example.gwent_projet.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.gwent_projet.entity.deck.Deck;
import com.example.gwent_projet.repository.DeckRepository;
import com.example.gwent_projet.services.DeckService;
import com.example.gwent_projet.services.dto.deck.DeckDTO;

@Service
public class DeckServiceImpl implements DeckService {
	
	private DeckRepository deckRepository;
	
	public DeckServiceImpl (DeckRepository deckRepository) {
		super();
		this.deckRepository = deckRepository;
	}
	
	// ---------------------------------------------------------------------------------------

	@Override
	public DeckDTO createDeck(Deck createDeck) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DeckDTO> getAllDecks() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DeckDTO getDeckById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DeckDTO updateDeck(Long id, Deck deck) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteDeckById(Long id) {
		// TODO Auto-generated method stub
		
	}

	
	
	
}
