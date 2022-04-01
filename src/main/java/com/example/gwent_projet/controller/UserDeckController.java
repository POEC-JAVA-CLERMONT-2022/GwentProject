package com.example.gwent_projet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.gwent_projet.services.UserDeckService;

// @RestController 
public class UserDeckController {

	// wip ...................
	
	@Autowired
	UserDeckService userDeckService;
	/*
	// Create deck ------------------------------------------------------------------------------
	@PostMapping("/user/{id}/deck/new")
	
	
	// Get deck ---------------------------------------------------------------------------------
	
	// all decks
	@GetMapping("/user/{id}/deck/")
	
	
	// by ID
	@GetMapping("/user/deck/{deckId}")
	
	// Update deck ------------------------------------------------------------------------------
	
	// add card to deck
	@GetMapping("/user/{id}/deck/{deckId}/edit")
	
	// delete card in deck
	@DeleteMapping("/user/{id}/deck/{deckId}/edit")
	
	// replace card in deck
	@GetMapping("/user/{id}/deck/{deckId}/edit")
	
	// rename deck
	@GetMapping("/user/{id}/deck/{deckId}/edit")

	// Delete deck ------------------------------------------------------------------------------
	@DeleteMapping("/user/{id}/deck/{deckId}/delete")
	public ResponseEntity<HttpStatus> deleteUserDeck(@PathVariable("id") Long userId, @PathVariable("deckId") Long deckId) {
		try {
			userDeckService.deleteUserDeckById(userId, deckId);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	*/
	
	
}
