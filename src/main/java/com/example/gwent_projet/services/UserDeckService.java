package com.example.gwent_projet.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.gwent_projet.entity.user.User;
import com.example.gwent_projet.entity.user.UserDeck;
import com.example.gwent_projet.services.dto.user.UserDTO;
import com.example.gwent_projet.services.dto.user.UserDeckDTO;

@Service
public interface UserDeckService {
	
	UserDTO createUserDeck(User user, UserDeck userDeck);

	List<UserDeckDTO> getAllUserDecks();

	UserDTO getUserDeckById(Long userId, Long deckId);

	UserDTO updateUserDeck(Long id, User user);

	void deleteUserDeckById(Long userId, Long deckId);

}
