package com.example.gwent_projet.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.gwent_projet.services.dto.UserDTO;
import com.example.gwent_projet.services.dto.UserDeckDTO;
import com.example.gwent_projet.entity.User;
import com.example.gwent_projet.entity.UserDeck;

@Service
public interface UserDeckService {
	
	UserDTO createUserDeck(User user, UserDeck userDeck);

	List<UserDeckDTO> getAllUserDecks();

	UserDTO getUserDeckById(Long userId, Long deckId);

	UserDTO updateUserDeck(Long id, User user);

	void deleteUserDeckById(Long userId, Long deckId);

}
