package com.example.gwent_projet.controller;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.gwent_projet.entity.card.CardDeck;
import com.example.gwent_projet.repository.CardDeckRepository;
import com.example.gwent_projet.services.CardDeckService;
import com.example.gwent_projet.services.dto.cardDeck.CardDeckDTO;
import com.example.gwent_projet.services.dto.cardDeck.CreateCardDeckDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@CrossOrigin(origins = "*")
@RequestMapping("cardDecks")
@RestController
@Tag(name = "Card Decks", description = "CardDeck API")
public class CardDeckController {

	@Autowired
	CardDeckRepository cardDeckRepository;
	@Autowired
	CardDeckService cardDeckService;

	// Get cardDeck ------------------------------------------------------------------------------
	// All cardDecks 
	@GetMapping("")
	@Operation(summary = "Get all Card Decks", description = "Get the entire list of decks that can be attributed to cards.", tags = { "Card Decks" }) 
	public ResponseEntity<List<CardDeckDTO>> getAllCardDeck(@RequestParam(required = false) Long id) {
		try {
			List<CardDeckDTO> cardDecks = new ArrayList<CardDeckDTO>();
			if (id == null)
				cardDecks = cardDeckService.getAllCardDecks();
			if (cardDecks.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(cardDecks, HttpStatus.OK);
		} catch (Exception e) {
			return ResponseEntity.status(400).build();
		}
	}


	// by ID
	@GetMapping("/{id}")
	@Operation(summary = "Get one Card Deck", description = "Get one of the decks that can be attributed to cards, by its id.", tags = { "Card Decks" }) 
	public ResponseEntity<CardDeckDTO> getCardDeckById(@PathVariable("id") long id) {
		try {
			CardDeckDTO findCardDeck = cardDeckService.getCardDeckById(id);
			return new ResponseEntity<>(findCardDeck, HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// Create Card Deck -----------------------------------------------------------------------------------------------------------------

	@PostMapping("")
	@Operation(summary = "Create a Card Deck", description = "Create a deck that can be attributed to cards.", tags = { "Card Decks" }) 
	public ResponseEntity<CardDeckDTO> createCardDeck(@RequestBody CreateCardDeckDTO createCardDeckDTO) {
		try {
			CardDeckDTO newCardDeck = cardDeckService.createCardDeck(createCardDeckDTO);
			return new ResponseEntity<>(newCardDeck, HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	// Update Card Deck -----------------------------------------------------------------------------------------------------------------
	// by ID
	@PutMapping("/{id}")
	@Operation(summary = "Update a Card Deck", description = "Updates a deck that can be attributed to cards.", tags = { "Card Decks" }) 
	public ResponseEntity<CardDeckDTO> updateCardDeckById(@PathVariable("id") Long id, @RequestBody CreateCardDeckDTO cardDeck) {

		try {
			CardDeckDTO updatedCardDeck = cardDeckService.updateCardDeck(id, cardDeck);
			return new ResponseEntity<>(updatedCardDeck, HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	// Delete user -----------------------------------------------------------------------------------------------------------------
	// by ID
	@DeleteMapping("/{id}")
	@Operation(summary = "Delete a Card Deck", description = "Deletes a deck that can be attributed to cards.", tags = { "Card Decks" }) 
	public ResponseEntity<CardDeck> deleteCardDeck(@RequestBody @PathVariable("id") Long id) {
		try {
			if (id != null){
				cardDeckService.deleteCardDeckById(id);
			}
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			return ResponseEntity.status(400).build();
		}
	}
}
