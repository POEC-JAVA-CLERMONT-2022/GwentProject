package com.example.gwent_projet.controller;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

import com.example.gwent_projet.entity.card.Card;
import com.example.gwent_projet.repository.CardRepository;
import com.example.gwent_projet.services.CardService;
import com.example.gwent_projet.services.dto.card.CardDTO;
import com.example.gwent_projet.services.dto.card.CreateCardDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@CrossOrigin(origins = "*")
@RequestMapping("cards")
@RestController
@Tag(name = "Cards", description = "Card API")
public class CardController {

	@Autowired
	CardRepository cardRepository;
	@Autowired
	CardService cardService;

	// Create card -----------------------------------------------------------------------------------------------------------------

	@PostMapping("")
	@Operation(summary = "Create a card", description = "Creates a Card, then returns a DTO of it.", tags = { "Cards" })
	public ResponseEntity<CardDTO> createCard(@RequestBody CreateCardDTO createCardDTO) {
		try {
			CardDTO newCard = cardService.createCard(createCardDTO);
			return new ResponseEntity<>(newCard, HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// Get card -----------------------------------------------------------------------------------------------------------------
	// all cards
	@GetMapping("")
	@Operation(summary = "Get all cards", description = "Returns a list of DTOs of all cards in the database.", tags = { "Cards" })
	public ResponseEntity<List<CardDTO>> getAllCards(@RequestParam(required = false) Long id) {
		try {
			List<CardDTO> cards = new ArrayList<CardDTO>();
			if (id == null)
				cards = cardService.getAllCards();
			if (cards.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(cards, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// by ID
	@GetMapping("/{id}")
	@Operation(summary = "Get one card", description = "Returns a DTO of a card by its id.", tags = { "Cards" })
	public ResponseEntity<CardDTO> getCardById(@PathVariable("id") long id) {
		try {
			CardDTO findCard = cardService.getCardById(id);
			return new ResponseEntity<>(findCard, HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// by CardDeck ID
	@GetMapping("/card-deck/{id}")
	@Operation(summary = "Get all cards of a deck", description = "Returns a list of DTOs of all cards in the database, that belong to a CardDeck.", tags = { "Cards" })
	public ResponseEntity<List<Card>> findAllByCardDeckId(@PathVariable("id") long id) {
		//List<Card> cards = new ArrayList<Card>(cardRepository.findAllByCardDeckId(id));
		try {
			List<Card> cards = new ArrayList<Card>();
			if (id > 0)
				cards.addAll(cardService.findCardsByCardDeck(id));
			if (cards.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(cards, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// by name
	@GetMapping("/card-name/{name}")
	@Operation(summary = "Get all cards with this name", description = "Returns a list of DTOs of all cards in the database, that have the chosen name.", tags = { "Cards" })
	public ResponseEntity<List<Card>> findCardsByName( String name) {
		try {
			List<Card> cards = new ArrayList<Card>();
			if (name != null)
				cards.addAll(cardService.findCardsByName(name));
			if (cards.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(cards, HttpStatus.OK);
		} catch (Exception e) {
			//return ResponseEntity.status(400).build();
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// Update card -----------------------------------------------------------------------------------------------------------------
	// by ID
	@PutMapping("/{id}")
	@Operation(summary = "Update a card", description = "Updates a card in database by its id, then returns a DTO of it.", tags = { "Cards" })
	public ResponseEntity<CardDTO> updateCardById( @PathVariable("id") Long id, @RequestBody CreateCardDTO card) {
		try {
			CardDTO updatedCard = cardService.updateCard(id, card);
			return new ResponseEntity<>(updatedCard, HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// Delete card -----------------------------------------------------------------------------------------------------------------
	// by ID
	@DeleteMapping("/{id}")
	@Operation(summary = "Delete a card", description = "Deletes a card in the database by its id.", tags = { "Cards" })
	public ResponseEntity<Card> deleteCard(@RequestBody @PathVariable("id") Long id) {
		try {
			if (id != null){
				cardService.deleteCardById(id);
			}
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
