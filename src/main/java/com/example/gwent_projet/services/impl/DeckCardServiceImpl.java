package com.example.gwent_projet.services.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.example.gwent_projet.entity.card.Card;
import com.example.gwent_projet.entity.deck.Deck;
import com.example.gwent_projet.entity.deck.DeckCard;
import com.example.gwent_projet.entity.pk.DeckCardPK;
import com.example.gwent_projet.repository.CardRepository;
import com.example.gwent_projet.repository.DeckCardRepository;
import com.example.gwent_projet.repository.DeckRepository;
import com.example.gwent_projet.services.DeckCardService;
import com.example.gwent_projet.services.dto.card.CardDTO;

import java.util.ArrayList;
import java.util.List;

@Service
public class DeckCardServiceImpl implements DeckCardService {

	private DeckRepository deckRepository;
	private CardRepository cardRepository;
	private DeckCardRepository deckCardRepository;

	public DeckCardServiceImpl (DeckRepository deckRepository, CardRepository cardRepository, DeckCardRepository deckCardRepository) {
		super();
		this.deckRepository = deckRepository;
		this.cardRepository = cardRepository;
		this.deckCardRepository = deckCardRepository;
	}

	// ---------------------------------------------------------------------------------------
	@Override
	public void addCardToDeck(Long deckId, Long cardId) {
		Deck deck = deckRepository.getById(deckId);
		Card card = cardRepository.getById(cardId);

		// create new primary key with those entities
		DeckCardPK deckCardPK = new DeckCardPK(deck, card);
		// create new deck of cards with that primary key
		DeckCard deckCard = new DeckCard(deckCardPK);
		// and save that deck of cards to its repository
		deckCardRepository.save(deckCard); // this deck is now binded to this card
	}

	@Override
	public List<CardDTO> getAllCardsInDeck(Long deckId) {
		// get the entire lit of cards corresponding to this deck
		ArrayList<DeckCard> deckCardsList = deckCardRepository.getAllCardsInDeck(deckId);
		// new, empty list of CardDTOs
		List<CardDTO> returnList = new ArrayList<>();

		for (int sweeper = 0; sweeper < deckCardsList.size(); sweeper++) {
			CardDTO returnCardDTO = new CardDTO();
			Card repoCard;
			// get the card
			repoCard = deckCardsList.get(sweeper).getId().getCard();
			// populate DTO values with retrieved user values
			BeanUtils.copyProperties(repoCard, returnCardDTO);
			// add newly populated DTO to DTO list
			returnList.add(sweeper, returnCardDTO);
		}

		return returnList;
	}

	@Override
	public void deleteOneCardFromDeck(Long deckId, Long cardId) {
		// not working: SQL ERROR
		deckCardRepository.deleteOneCardFromDeck(deckId, cardId);
	}

	@Override
	public void deleteAllCardsFromDeck(Long deckId) {
		// not working: SQL ERROR
		deckCardRepository.deleteAllCardsFromDeck(deckId);
	}
}
