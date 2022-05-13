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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@CrossOrigin(origins = "*")
@RequestMapping("/user")
@RestController 
@Tag(name = "Users", description = "User API")
public class UserController {

	@Autowired
	UserService userService;

	// Create user -----------------------------------------------------------------------------------------------------------------
	
	// replaced by auth functions
	@Deprecated
	@PostMapping("")
	@Operation(summary = "Create a new user", description = "Deprecated, replaced by the Auth API. "
			+ "<br />" + "Please do not use this, as it will create an user without a role and a non-encrypted password.", tags = { "Users" })
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
	@Operation(summary = "Get all users", description = "Returns a list of DTOs of all users in the database.", tags = { "Users" })
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
	@Operation(summary = "Get one user", description = "Returns a DTO of an user by its id.", tags = { "Users" })
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
	
	/*
	 * !!!!!!!!!!!!!!!!!!!!!!!!!!!
	 * 
	 * PLEASE UPDATE THIS!
	 * It needs to re-validate the password with JWT!
	 * 
	 * !!!!!!!!!!!!!!!!!!!!!!!!!!!
	 */
	
	@PutMapping("/{user_id}")
	@Operation(summary = "Update an user", description = "Updates an user in database by its id, then returns a DTO of it.", tags = { "Users" })
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
	@Operation(summary = "Delete an user", description = "Deletes an user in the database by its id.", tags = { "Users" })
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
		@Operation(summary = "Add a favorite to an user", description = "Adds a 'favorite' relation between an user and a card by their ids.", tags = { "Users" })
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
		@Operation(summary = "Get all favorites from an user", description = "Retrieves all the favorited cards of an user by the user's id.", tags = { "Users" })
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
		@Operation(summary = "Delete a favorite of an user", description = "Deletes one 'favorite' card of an user by their ids.", tags = { "Users" })
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
		@Operation(summary = "Delete all the favorites of an user", description = "Deletes all 'favorite' cards of an user by the user id.", tags = { "Users" })
		public ResponseEntity<HttpStatus> deleteAllUserFavorites(@PathVariable("user_id") Long userId) {
			try {
				userService.deleteAllUserFavorites(userId);
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			} catch (Exception e) {
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
	
	
	
}
