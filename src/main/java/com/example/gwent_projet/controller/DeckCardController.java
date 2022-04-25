package com.example.gwent_projet.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.gwent_projet.services.DeckCardService;
import com.example.gwent_projet.services.dto.card.CardDTO;

@RequestMapping("/deck/cards")
@RestController
public class DeckCardController {
	
	@Autowired
	DeckCardService deckCardService;

	// Add card to deck ------------------------------------------------------------------------------------------------------------

	@PutMapping("/add")
	public ResponseEntity<HttpStatus> addCardToDeck(Long deckId, Long cardId) {
		try {
			deckCardService.addCardToDeck(deckId, cardId);
			return new ResponseEntity<>(HttpStatus.CREATED);
		} catch (Exception e) {
			// return null value with ERROR HTTP status
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// Get all cards from deck -----------------------------------------------------------------------------------------------------

	@GetMapping("/")
	public ResponseEntity<ArrayList<CardDTO>> getAllCardsInDeck(Long deckId) {
		try {
			// new list of users
			ArrayList<CardDTO> cards = new ArrayList<CardDTO>();
			// get all users from the repository and add them to the list
			cards.addAll(deckCardService.getAllCardsInDeck(deckId));
			if (cards.isEmpty()) {
				// if no results, return NO CONTENT HTTP status
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			// return all users with OK HTTP status
			return new ResponseEntity<>(cards, HttpStatus.OK);
		} catch (Exception e) {
			// return null value with ERROR HTTP status
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	// Delete card from deck -------------------------------------------------------------------------------------------------------
	// All cards
	@DeleteMapping("/remove/all")
	public ResponseEntity<HttpStatus> deleteOneCardFromDeck(Long deckId) {
		try {
			deckCardService.deleteAllCardsFromDeck(deckId);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	// One card
	@DeleteMapping("/remove")
	public ResponseEntity<HttpStatus> deleteAllCardsFromDeck(Long deckId, Long cardId) {
		try {
			deckCardService.deleteOneCardFromDeck(deckId, cardId);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
