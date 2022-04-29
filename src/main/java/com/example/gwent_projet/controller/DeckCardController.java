package com.example.gwent_projet.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.gwent_projet.services.DeckCardService;
import com.example.gwent_projet.services.dto.deckCard.DeckCardCreationDTO;
import com.example.gwent_projet.services.dto.deckCard.DeckCardDTO;

@CrossOrigin(origins = "*")
@RequestMapping("/deck/cards")
@RestController
public class DeckCardController {
	
	@Autowired
	DeckCardService deckCardService;

	// Add card to deck ------------------------------------------------------------------------------------------------------------

	@PutMapping("/add")
	public ResponseEntity<DeckCardCreationDTO> addCardToDeck(Long deckId, Long cardId) {
		try {
			DeckCardCreationDTO newDeckCard = deckCardService.addCardToDeck(deckId, cardId);
			return new ResponseEntity<>(newDeckCard, HttpStatus.CREATED);
		} catch (Exception e) {
			// return null value with ERROR HTTP status
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// Get all cards from deck -----------------------------------------------------------------------------------------------------

	@GetMapping("/")
	public ResponseEntity<ArrayList<DeckCardDTO>> getAllCardsInDeck(Long deckId) {
		try {
			// new list of cards
			ArrayList<DeckCardDTO> cards = new ArrayList<DeckCardDTO>();
			// get all cards from the repository and add them to the list
			cards.addAll(deckCardService.getAllCardsInDeck(deckId));
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
	// All cards
	@DeleteMapping("/remove")
	public ResponseEntity<HttpStatus> deleteOneCardFromDeck(Long deckId, Long cardId) {
		try {
			deckCardService.deleteOneCardFromDeck(deckId, cardId);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	// One card
	@DeleteMapping("/remove/all")
	public ResponseEntity<HttpStatus> deleteAllCardsFromDeck(Long deckId) {
		try {
			deckCardService.deleteAllCardsFromDeck(deckId);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
