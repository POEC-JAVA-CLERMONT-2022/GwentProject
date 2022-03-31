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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.gwent_projet.dto.UserDTO;
import com.example.gwent_projet.models.User;
import com.example.gwent_projet.services.UserService;

@RestController 
public class UserController {

	@Autowired
	UserService userService;

	// Create user -----------------------------------------------------------------------------------------------------------------
	@PostMapping("/user/new")
	public ResponseEntity<UserDTO> createUser(@RequestBody User user) {
		try {
			// create user in repository with data provided by method
			UserDTO newUser = userService.createUser(user);
			// user to be returned (TEMP)
			// then return a response entity with this user, and the CREATED HTTP status
			return new ResponseEntity<>(newUser, HttpStatus.CREATED);
		} catch (Exception e) {
			// return null value with ERROR HTTP status
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// Get user -----------------------------------------------------------------------------------------------------------------
	// All users
	@GetMapping("/user")
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
	@GetMapping("/user/{id}")
	public ResponseEntity<UserDTO> getUserById(@PathVariable("id") Long id) {
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
	@PostMapping("/user/update/{id}")
	public ResponseEntity<UserDTO> updateUserById(@PathVariable("id") Long id, @RequestBody User user) {
		try {
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
	@DeleteMapping("/user/delete/{id}")
	public ResponseEntity<HttpStatus> deleteUser(@PathVariable("id") Long id) {
		try {
			userService.deleteUserById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}