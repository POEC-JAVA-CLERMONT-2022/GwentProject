package com.example.gwent_projet.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.gwent_projet.services.DeckService;
import com.example.gwent_projet.services.dto.deck.DeckCardCreationDTO;
import com.example.gwent_projet.services.dto.deck.DeckCardDTO;
import com.example.gwent_projet.services.dto.deck.DeckCreationDTO;
import com.example.gwent_projet.services.dto.deck.DeckDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@CrossOrigin(origins = "*")
@RequestMapping("/deck")
@RestController
@Tag(name = "Decks", description = "Deck API")
public class DeckController {

	@Autowired
	DeckService deckService;

	// Create deck -----------------------------------------------------------------------------------------------------------------
	@PostMapping("")
	@Operation(summary = "Create a deck", description = "Creates a Deck and returns a DTO of it.", tags = { "Decks" }) 
	public ResponseEntity<DeckDTO> createDeck(Long userId, @RequestBody DeckCreationDTO deck) {
		try {
			// create deck in repository with data provided by method
			DeckDTO newDeck = deckService.createDeck(userId, deck);
			// then return a response entity with this deck, and the CREATED HTTP status
			return new ResponseEntity<>(newDeck, HttpStatus.CREATED);
		} catch (Exception e) {
			// return null value with ERROR HTTP status
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// Get deck -----------------------------------------------------------------------------------------------------------------
	// All decks
	@GetMapping("")
	@Operation(summary = "Get all decks", description = "Returns a list of DTOs of all Decks in the database.", tags = { "Decks" }) 
	public ResponseEntity<List<DeckDTO>> getAllDecks() {
		try {
			// new list of users
			List<DeckDTO> decks = new ArrayList<DeckDTO>();
			// get all users from the repository and add them to the list
			decks.addAll(deckService.getAllDecks());
			if (decks.isEmpty()) {
				// if no results, return NO CONTENT HTTP status
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			// return all users with OK HTTP status
			return new ResponseEntity<>(decks, HttpStatus.OK);
		} catch (Exception e) {
			// return null value with ERROR HTTP status
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// by ID
	@GetMapping("/{deck_id}")
	@Operation(summary = "Get one deck", description = "Returns a DTO of a deck by its id.", tags = { "Decks" })
	public ResponseEntity<DeckDTO> getDeckById(@PathVariable("deck_id") Long id) {
		try { 
			DeckDTO searchResult = deckService.getDeckById(id);
			return new ResponseEntity<>(searchResult, HttpStatus.OK);
		} catch (Exception e) {
			// return null value with ERROR HTTP status
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// Update deck -----------------------------------------------------------------------------------------------------------------
	// By ID
	@PutMapping("/{deck_id}")
	@Operation(summary = "Update an deck", description = "Updates a deck in database by its id, then returns a DTO of it.", tags = { "Decks" })
	public ResponseEntity<DeckDTO> updateDeckById(@PathVariable("deck_id") Long id, @RequestBody DeckCreationDTO deck) {
		try {
			// replace user at this ID with the new user
			DeckDTO updatedDeck = deckService.updateDeck(id, deck);
			// then return a response entity with this user, and the CREATED HTTP status
			return new ResponseEntity<>(updatedDeck, HttpStatus.CREATED);
		} catch (Exception e) {
			// return null value with ERROR HTTP status
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// Delete deck -----------------------------------------------------------------------------------------------------------------
	// By ID
	@DeleteMapping("/{deck_id}")
	@Operation(summary = "Delete an user", description = "Deletes a deck in the database by its id.", tags = { "Decks" })
	public ResponseEntity<HttpStatus> deleteDeck(@PathVariable("deck_id") Long id) {
		try {
			deckService.deleteDeckById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// ---- Deck of cards -----
	// Add card to deck ------------------------------------------------------------------------------------------------------------

	@PostMapping("{deck_id}/card/{card_id}")
	@Operation(summary = "Add a card to a deck", description = "Adds a 'deckCard' relation between a deck and a card by their ids.", tags = { "Decks" })
	public ResponseEntity<DeckCardCreationDTO> addCardToDeck(@PathVariable("deck_id") Long deckId, @PathVariable("card_id") Long cardId) {
		try {
			DeckCardCreationDTO newDeckCard = deckService.addCardToDeck(deckId, cardId);
			return new ResponseEntity<>(newDeckCard, HttpStatus.CREATED);
		} catch (Exception e) {
			// return null value with ERROR HTTP status
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// Get all cards from deck -----------------------------------------------------------------------------------------------------

	@GetMapping("{deck_id}/cards")
	@Operation(summary = "Get all cards from a deck", description = "Retrieves all the cards from a deck by the deck's id.", tags = { "Decks" })
	public ResponseEntity<ArrayList<DeckCardDTO>> getAllCardsInDeck(@PathVariable("deck_id") Long deckId) {
		try {
			// new list of cards
			ArrayList<DeckCardDTO> cards = new ArrayList<DeckCardDTO>();
			// get all cards from the repository and add them to the list
			cards.addAll(deckService.getAllCardsInDeck(deckId));
			if (cards.isEmpty()) {
				// if no results, return NO CONTENT HTTP status
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			// return all cards with OK HTTP status
			return new ResponseEntity<>(cards, HttpStatus.OK);
		} catch (Exception e) {
			// return null value with ERROR HTTP status
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}


	// Delete card from deck -------------------------------------------------------------------------------------------------------
	// One card
	@DeleteMapping("/{deck_id}/card/{card_id}")
	@Operation(summary = "Delete a card from a deck", description = "Deletes one card from a deck by their ids.", tags = { "Decks" })
	public ResponseEntity<HttpStatus> deleteOneCardFromDeck(@PathVariable("deck_id") Long deckId, @PathVariable("card_id") Long cardId) {
		try {
			deckService.deleteOneCardFromDeck(deckId, cardId);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// All cards
	@DeleteMapping("/{deck_id}/cards")
	@Operation(summary = "Delete all the cards from a deck", description = "Deletes all cards from a deck by the deck id.", tags = { "Decks" })
	public ResponseEntity<HttpStatus> deleteAllCardsFromDeck(@PathVariable("deck_id") Long deckId) {
		try {
			deckService.deleteAllCardsFromDeck(deckId);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
