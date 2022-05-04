package com.example.gwent_projet.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.gwent_projet.services.UserService;
import com.example.gwent_projet.services.dto.card.CardDTO;
import com.example.gwent_projet.services.dto.user.FavoritesDTO;
import com.example.gwent_projet.services.dto.user.UserCreationDTO;
import com.example.gwent_projet.services.dto.user.UserDTO;

@CrossOrigin(origins = "*")
@RequestMapping("/user")
@RestController 
public class UserController {

	@Autowired
	UserService userService;

	// Create user -----------------------------------------------------------------------------------------------------------------
	@PostMapping("")
	// actual object is used instead of DTO so that the password (and more) can be set on creation
	// -- what about the id?
	// -- is this a security issue?
	public ResponseEntity<UserDTO> createUser(@RequestBody UserCreationDTO user) {
		try {
			
			if (user == null) {
				return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
			}
			
			// create user in repository with data provided by method
			UserDTO newUser = userService.createUser(user);
			// then return a response entity with this user, and the CREATED HTTP status
			return new ResponseEntity<>(newUser, HttpStatus.CREATED);
		} catch (Exception e) {
			// return null value with ERROR HTTP status
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// Get user -----------------------------------------------------------------------------------------------------------------
	// All users
	@GetMapping("")
	public ResponseEntity<List<UserDTO>> getAllUsers() {
		try {
			// new list of users
			List<UserDTO> users = new ArrayList<UserDTO>();
			// get all users from the repository and add them to the list
			users.addAll(userService.getAllUsers());
			
			if (users.isEmpty()) {
				// if no results, return NO CONTENT HTTP status
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			
			// return all users with OK HTTP status
			return new ResponseEntity<>(users, HttpStatus.OK);
		} catch (Exception e) {
			// return null value with ERROR HTTP status
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// by ID
	@GetMapping("/{user_id}")
	public ResponseEntity<UserDTO> getUserById(@PathVariable("user_id") Long id) {
		try { 
			UserDTO searchResult = userService.getUserById(id);
			return new ResponseEntity<>(searchResult, HttpStatus.OK);
		} catch (Exception e) {
			// return null value with ERROR HTTP status
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// Update user -----------------------------------------------------------------------------------------------------------------
	// By ID
	@PutMapping("/{user_id}")
	public ResponseEntity<UserDTO> updateUserById(@PathVariable("user_id") Long id, @RequestBody UserCreationDTO user) {
		try {
			
			if (user == null) {
				return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
			}
			
			// replace user at this ID with the new user
			UserDTO updatedUser = userService.updateUser(id, user);
			// then return a response entity with this user, and the CREATED HTTP status
			return new ResponseEntity<>(updatedUser, HttpStatus.CREATED);
		} catch (Exception e) {
			// return null value with ERROR HTTP status
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// Delete user -----------------------------------------------------------------------------------------------------------------
	// By ID
	@DeleteMapping("/{user_id}")
	public ResponseEntity<HttpStatus> deleteUser(@PathVariable("user_id") Long id) {
		try {
			userService.deleteUserById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// ---- Favorites ----
	// Add favorite ----------------------------------------------------------------------------------------------------------------

		@PostMapping("/{user_id}/favorites/{card_id}")
		public ResponseEntity<FavoritesDTO> addFavorite(@PathVariable("user_id") Long userId, @PathVariable("card_id") Long cardId) {
			try {
				FavoritesDTO newFavorite = userService.addFavorite(userId, cardId);
				return new ResponseEntity<>(newFavorite, HttpStatus.CREATED);
			} catch (Exception e) {
				// return null value with ERROR HTTP status
				return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}

		// Get all favorites from user -------------------------------------------------------------------------------------------------

		@GetMapping("/{user_id}/favorites")
		public ResponseEntity<ArrayList<CardDTO>> getAllUserFavorites(@PathVariable("user_id") Long userId) {
			try {
				// new list of cards
				ArrayList<CardDTO> favorites = new ArrayList<CardDTO>();
				// get all cards from the repository and add them to the list
				favorites.addAll(userService.getAllUserFavorites(userId));
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


		// Delete favorites from user --------------------------------------------------------------------------------------------------
		// One favorite
		@DeleteMapping("/{user_id}/favorites/{card_id}")
		public ResponseEntity<HttpStatus> deleteOneUserFavorite(@PathVariable("user_id") Long userId, @PathVariable("card_id") Long cardId) {
			try {
				userService.deleteOneUserFavorite(userId, cardId);
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			} catch (Exception e) {
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}

		// All favorites
		@DeleteMapping("/{user_id}/favorites")
		public ResponseEntity<HttpStatus> deleteAllUserFavorites(@PathVariable("user_id") Long userId) {
			try {
				userService.deleteAllUserFavorites(userId);
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			} catch (Exception e) {
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}

}
