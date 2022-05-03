package com.example.gwent_projet.services.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.gwent_projet.entity.card.Card;
import com.example.gwent_projet.entity.deck.Deck;
import com.example.gwent_projet.entity.deck.DeckCard;
import com.example.gwent_projet.entity.pk.DeckCardPK;
import com.example.gwent_projet.entity.user.User;
import com.example.gwent_projet.repository.CardRepository;
import com.example.gwent_projet.repository.DeckCardRepository;
import com.example.gwent_projet.repository.DeckRepository;
import com.example.gwent_projet.repository.UserRepository;
import com.example.gwent_projet.services.DeckService;
import com.example.gwent_projet.services.dto.card.CardDTO;
import com.example.gwent_projet.services.dto.deck.DeckCardCreationDTO;
import com.example.gwent_projet.services.dto.deck.DeckCardDTO;
import com.example.gwent_projet.services.dto.deck.DeckCreationDTO;
import com.example.gwent_projet.services.dto.deck.DeckDTO;
import com.example.gwent_projet.services.dto.user.UserDeckDTO;

@Service
public class DeckServiceImpl implements DeckService {

	private DeckRepository deckRepository;
	private CardRepository cardRepository;
	private UserRepository userRepository;
	private DeckCardRepository deckCardRepository;

	public DeckServiceImpl (DeckRepository deckRepository, CardRepository cardRepository, UserRepository userRepository, DeckCardRepository deckCardRepository) {
		super();
		this.deckRepository = deckRepository;
		this.userRepository = userRepository;
		this.cardRepository = cardRepository;
		this.deckCardRepository = deckCardRepository;
	}

	// ---------------------------------------------------------------------------------------

	@Override
	public DeckDTO createDeck(Long userId, DeckCreationDTO createDeck) {
		Deck deck = new Deck(); 
		// set creation date to current time
		deck.setCreatedAt(LocalDate.now());

		BeanUtils.copyProperties(createDeck, deck);

		// get the user which created this deck
		User owner = userRepository.findById(userId).orElse(null);
		// then set those values in the new deck
		deck.setOwner(owner);
		deckRepository.save(deck);

		// new UserDeckDTO to display the return value
		UserDeckDTO returnOwner = new UserDeckDTO();
		BeanUtils.copyProperties(owner, returnOwner);

		// DTO to be returned, populated with new user values
		DeckDTO newDeck = new DeckDTO(null, null, returnOwner);
		BeanUtils.copyProperties(deck, newDeck);
		return newDeck;
	}

	@Override
	public List<DeckDTO> getAllDecks() {
		// get the entire list of decks from the repo
		List<Deck> repoList = deckRepository.findAll();
		// new, empty list of DeckDTOs
		List<DeckDTO> returnList = new ArrayList<>();


		// for every favorite card in the list, populate a DTO and add it to the DTO list
		for (Deck decks : repoList) {
			UserDeckDTO returnOwner = new UserDeckDTO(
					decks.getOwner().getId(),
					decks.getOwner().getUsername()
					);
			DeckDTO returnDeckDTO  = new DeckDTO(null , null, returnOwner);
			// populate DTO values with retrieved user values
			BeanUtils.copyProperties(decks, returnDeckDTO);
			// add newly populated DTO to DTO list
			returnList.add(returnDeckDTO);
		}
		// then return the new DTO list
		return returnList;
	}

	@Override
	public DeckDTO getDeckById(Long id) {
		Deck deckSearch = deckRepository.findById(id).orElse(null);
		// DTO to be returned, populated with new user values
		UserDeckDTO returnOwner = new UserDeckDTO(
				deckSearch.getOwner().getId(),
				deckSearch.getOwner().getUsername()
				);

		DeckDTO searchResult  = new DeckDTO(null , null, returnOwner);
		BeanUtils.copyProperties(deckSearch, searchResult);
		return searchResult;
	}

	@Override
	public DeckDTO updateDeck(Long id, DeckCreationDTO updatedDeck) {
		Deck deck = deckRepository.getById(id);

		BeanUtils.copyProperties(updatedDeck, deck); // important: DTO needs getters and setters for this to work
		deckRepository.save(deck);

		UserDeckDTO returnOwner = new UserDeckDTO(
				deck.getOwner().getId(),
				deck.getOwner().getUsername()
				);

		DeckDTO deckReturn = new DeckDTO(null , null, returnOwner);
		BeanUtils.copyProperties(deck, deckReturn);

		return deckReturn;
	}

	@Override
	public void deleteDeckById(Long id) {
		deckRepository.deleteById(id);
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


		for (DeckCard deckCards : deckCardsList) {
			// get the card and convert it to a DTO
			Card repoCard = deckCards.getId().getCard();
			CardDTO returnCard = new CardDTO();
			BeanUtils.copyProperties(repoCard, returnCard);

			// get the amount of cards
			int nbRepoCard = deckCards.getNbItems();

			// compile both into a DeckCard DTO
			DeckCardDTO returnDeckCardDTO = new DeckCardDTO(returnCard, nbRepoCard);
			// add newly populated DTO to DTO list
			returnList.add(returnDeckCardDTO);
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
