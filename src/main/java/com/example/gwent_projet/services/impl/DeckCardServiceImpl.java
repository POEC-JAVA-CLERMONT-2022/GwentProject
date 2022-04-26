package com.example.gwent_projet.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.gwent_projet.entity.card.Card;
import com.example.gwent_projet.entity.deck.Deck;
import com.example.gwent_projet.entity.deckCard.DeckCard;
import com.example.gwent_projet.entity.pk.DeckCardPK;
import com.example.gwent_projet.repository.CardRepository;
import com.example.gwent_projet.repository.DeckCardRepository;
import com.example.gwent_projet.repository.DeckRepository;
import com.example.gwent_projet.services.DeckCardService;
import com.example.gwent_projet.services.dto.card.CardDTO;
import com.example.gwent_projet.services.dto.deckCard.DeckCardCreationDTO;
import com.example.gwent_projet.services.dto.deckCard.DeckCardDTO;

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
	public DeckCardCreationDTO addCardToDeck(Long deckId, Long cardId) {
		Deck deck = deckRepository.getById(deckId);
		Card card = cardRepository.getById(cardId);
		int nbItems = 1; // there is always at least one card

		// create new primary key with those entities
		DeckCardPK deckCardPK = new DeckCardPK(deck, card);
		// create new deck of cards with that primary key
		DeckCard deckCard = new DeckCard(deckCardPK, nbItems);

		// get the result or lack thereof of the repository at this deckCardPK ID
		DeckCard returnValue = deckCardRepository.findById(deckCardPK).orElse(null);
		// if there's already an entry where we are trying to save
		if (returnValue != null) {
			do {
				deckCard.setNbItems(nbItems++); // increase amount of cards by one
				// as long as we are inferior or equal to the current amount of cards in database
			} while (deckCard.getNbItems() <= deckCardRepository.findById(deckCardPK).get().getNbItems());
		}

		deckCardRepository.save(deckCard); // this deck is now binded to this card

		// populate DTO with the values saved in repository and return it
		DeckCardCreationDTO returnDeckCardCreationDTO = new DeckCardCreationDTO(
				deckCardRepository.findById(deckCardPK).get().getId().getCard().getId(),
				deckCardRepository.findById(deckCardPK).get().getId().getDeck().getId(),
				deckCardRepository.findById(deckCardPK).get().getNbItems()
				);
		return returnDeckCardCreationDTO;
	}

	@Override
	public List<DeckCardDTO> getAllCardsInDeck(Long deckId) {
		// get the entire lit of cards corresponding to this deck
		ArrayList<DeckCard> deckCardsList = deckCardRepository.getAllCardsInDeck(deckId);

		// new, empty list of CardDTOs
		List<DeckCardDTO> returnList = new ArrayList<>();

		for (int sweeper = 0; sweeper < deckCardsList.size(); sweeper++) {
			// get the card and convert it to a DTO
			Card repoCard = deckCardsList.get(sweeper).getId().getCard();
			CardDTO returnCard = new CardDTO();
			BeanUtils.copyProperties(repoCard, returnCard);

			// get the amount of cards
			int nbRepoCard = deckCardsList.get(sweeper).getNbItems();

			// compile both into a DeckCard DTO
			DeckCardDTO returnDeckCardDTO = new DeckCardDTO(returnCard, nbRepoCard);

			// add newly populated DTO to DTO list
			returnList.add(sweeper, returnDeckCardDTO);
		}
		return returnList;
	}

	@Override
	@Transactional
	public void deleteOneCardFromDeck(Long deckId, Long cardId) {
		Deck deck = deckRepository.getById(deckId);
		Card card = cardRepository.getById(cardId);
		// create new primary key with those entities
		DeckCardPK deckCardPK = new DeckCardPK(deck, card);

		// get the result or lack thereof of the repository at this deckCardPK ID
		DeckCard returnValue = deckCardRepository.findById(deckCardPK).orElse(null);

		// if there's already an entry where we are trying to delete
		// -- and nbItems is greater than one
		if (returnValue != null && returnValue.getNbItems() > 1) {
			// substract one from nbItems and save
			returnValue.setNbItems(returnValue.getNbItems() - 1);
			deckCardRepository.save(returnValue);
		// otherwise delete the entry
		} else {
			deckCardRepository.deleteOneCardFromDeck(deckId, cardId);
		}
	}

	@Override
	@Transactional
	public void deleteAllCardsFromDeck(Long deckId) {
		deckCardRepository.deleteAllCardsFromDeck(deckId);
	}
}
