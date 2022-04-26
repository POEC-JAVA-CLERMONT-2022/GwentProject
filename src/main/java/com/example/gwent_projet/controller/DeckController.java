package com.example.gwent_projet.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.gwent_projet.services.DeckService;
import com.example.gwent_projet.services.dto.deck.DeckCreationDTO;
import com.example.gwent_projet.services.dto.deck.DeckDTO;

@RequestMapping("/deck")
@RestController
public class DeckController {

	@Autowired
	DeckService deckService;

	// Create deck -----------------------------------------------------------------------------------------------------------------
	@PostMapping("/new")
	public ResponseEntity<DeckDTO> createDeck(Long userId, @RequestBody DeckCreationDTO deck) {
		try {
			// create deck in repository with data provided by method
			DeckDTO newDeck = deckService.createDeck(userId, deck);
			// then return a response entity with this deck, and the CREATED HTTP status
			return new ResponseEntity<>(newDeck, HttpStatus.CREATED);
		} catch (Exception e) {
			// return null value with ERROR HTTP status
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// Get deck -----------------------------------------------------------------------------------------------------------------
	// All decks
	@GetMapping("")
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
	@GetMapping("/{id}")
	public ResponseEntity<DeckDTO> getDeckById(@PathVariable("id") Long id) {
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
	@PutMapping("/update/{id}")
	public ResponseEntity<DeckDTO> updateDeckById(@PathVariable("id") Long id, @RequestBody DeckCreationDTO deck) {
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
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<HttpStatus> deleteDeck(@PathVariable("id") Long id) {
		try {
			deckService.deleteDeckById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}