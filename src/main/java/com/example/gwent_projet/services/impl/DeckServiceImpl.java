package com.example.gwent_projet.services.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.example.gwent_projet.entity.deck.Deck;
import com.example.gwent_projet.entity.user.User;
import com.example.gwent_projet.repository.DeckRepository;
import com.example.gwent_projet.repository.UserRepository;
import com.example.gwent_projet.services.DeckService;
import com.example.gwent_projet.services.dto.deck.DeckCreationDTO;
import com.example.gwent_projet.services.dto.deck.DeckDTO;
import com.example.gwent_projet.services.dto.user.UserDeckDTO;

@Service
public class DeckServiceImpl implements DeckService {

	private DeckRepository deckRepository;
	private UserRepository userRepository;

	public DeckServiceImpl (DeckRepository deckRepository, UserRepository userRepository) {
		super();
		this.deckRepository = deckRepository;
		this.userRepository = userRepository;
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
}
