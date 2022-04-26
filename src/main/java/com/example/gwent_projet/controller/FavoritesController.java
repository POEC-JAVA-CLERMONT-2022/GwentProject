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

import com.example.gwent_projet.services.FavoritesService;
import com.example.gwent_projet.services.dto.favorites.FavoritesDTO;

@RequestMapping("/favorites")
@RestController
public class FavoritesController {

	@Autowired
	FavoritesService favoritesService;

	// Add favorite ----------------------------------------------------------------------------------------------------------------

	@PutMapping("/add")
	public ResponseEntity<FavoritesDTO> addFavorite(Long userId, Long cardId) {
		try {
			FavoritesDTO newFavorite = favoritesService.addFavorite(userId, cardId);
			return new ResponseEntity<>(newFavorite, HttpStatus.CREATED);
		} catch (Exception e) {
			// return null value with ERROR HTTP status
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// Get all favorites from user -------------------------------------------------------------------------------------------------

	@GetMapping("/")
	public ResponseEntity<ArrayList<FavoritesDTO>> getAllUserFavorites(Long userId) {
		try {
			// new list of cards
			ArrayList<FavoritesDTO> favorites = new ArrayList<FavoritesDTO>();
			// get all cards from the repository and add them to the list
			favorites.addAll(favoritesService.getAllUserFavorites(userId));
			if (favorites.isEmpty()) {
				// if no results, return NO CONTENT HTTP status
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			// return all cards with OK HTTP status
			return new ResponseEntity<>(favorites, HttpStatus.OK);
		} catch (Exception e) {
			// return null value with ERROR HTTP status
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}


	// Delete card from deck -------------------------------------------------------------------------------------------------------
	// All cards
	@DeleteMapping("/remove")
	public ResponseEntity<HttpStatus> deleteOneUserFavorite(Long userId, Long cardId) {
		try {
			favoritesService.deleteOneUserFavorite(userId, cardId);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// One card
	@DeleteMapping("/remove/all")
	public ResponseEntity<HttpStatus> deleteAllUserFavorites(Long userId) {
		try {
			favoritesService.deleteAllUserFavorites(userId);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}


}
