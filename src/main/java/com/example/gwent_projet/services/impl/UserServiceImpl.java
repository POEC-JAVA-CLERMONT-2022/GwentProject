package com.example.gwent_projet.services.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.example.gwent_projet.entity.card.Card;
import com.example.gwent_projet.entity.pk.UserCardPK;
import com.example.gwent_projet.entity.user.Favorites;
import com.example.gwent_projet.entity.user.User;
import com.example.gwent_projet.repository.CardRepository;
import com.example.gwent_projet.repository.FavoritesRepository;
import com.example.gwent_projet.repository.UserRepository;
import com.example.gwent_projet.services.UserService;
import com.example.gwent_projet.services.dto.card.CardDTO;
import com.example.gwent_projet.services.dto.user.FavoritesDTO;
import com.example.gwent_projet.services.dto.user.UserCreationDTO;
import com.example.gwent_projet.services.dto.user.UserDTO;

@Service
public class UserServiceImpl implements UserService {

	private UserRepository userRepository;
	private CardRepository cardRepository;
	private FavoritesRepository favoritesRepository;

	public UserServiceImpl (UserRepository userRepository, CardRepository cardRepository, FavoritesRepository favoritesRepository) {
		super();
		this.userRepository = userRepository;
		this.cardRepository = cardRepository;
		this.favoritesRepository = favoritesRepository;
	}

	// ---------------------------------------------------------------------------------------

	@Override
	public UserDTO createUser(UserCreationDTO createUser) {

		User user = new User(0, "", "", ""); // always set role to 0 on creation. might change if more roles are added
		BeanUtils.copyProperties(createUser, user);

		userRepository.save(user);
		// DTO to be returned, populated with new user values
		UserDTO newUser = new UserDTO();
		BeanUtils.copyProperties(user, newUser);
		return newUser;
	}

	@Override
	public List<UserDTO> getAllUsers() {
		// get the entire list of users from the repo
		List<User> repoList = userRepository.findAll();
		// new, empty list of UserDTOs
		List<UserDTO> returnList = new ArrayList<>();

		// for every user in the list, populate a DTO and add it to the DTO list
		for (User repoUser : repoList) {
			UserDTO returnUserDTO  = new UserDTO();
			BeanUtils.copyProperties(repoUser, returnUserDTO);
			returnList.add(returnUserDTO);
		}

		// then return the new DTO list
		return returnList;
	}

	@Override
	public UserDTO getUserById(Long id) {
		// getById only returns a reference to the user, and not the actual entry in the DB.
		// this is not what we need and so we do not use it
		// instead we use findById which also lets us return a null value if nothing is found
		User userSearch = userRepository.findById(id).orElse(null);
		// DTO to be returned, populated with new user values
		UserDTO searchResult = new UserDTO();
		BeanUtils.copyProperties(userSearch, searchResult);
		return searchResult;
	}


	@Override
	public void deleteUserById(Long id) {
		userRepository.deleteById(id);
	}

	@Override
	@Transactional
	public UserDTO updateUser(Long id, UserCreationDTO newUser) {
		User user = userRepository.getById(id);

		BeanUtils.copyProperties(newUser, user); // important: DTO needs getters and setters for this to work
		userRepository.save(user);

		UserDTO userReturn = new UserDTO();
		BeanUtils.copyProperties(user, userReturn);

		return userReturn;
	}

	// ---------------------------------------------------------------------------------------

	@Override
	public FavoritesDTO addFavorite(Long userId, Long cardId) {
		User user = userRepository.getById(userId);
		Card card = cardRepository.getById(cardId);

		// create new primary key with those entities
		UserCardPK userCardPK = new UserCardPK(user, card);
		// create new favorite with that primary key
		Favorites favorite = new Favorites(userCardPK);

		favoritesRepository.save(favorite); // new favorite

		// DTO to be returned
		// create user and card DTO to populate favorites DTO
		UserDTO returnUser = new UserDTO();
		CardDTO returnCard = new CardDTO();
		BeanUtils.copyProperties(favoritesRepository.findById(userCardPK).get().getId().getUser(), returnUser);
		BeanUtils.copyProperties(favoritesRepository.findById(userCardPK).get().getId().getCard(), returnCard);
		// populate FavoritesDTO and return it
		FavoritesDTO returnFavoriteDTO = new FavoritesDTO(returnUser, returnCard);

		return returnFavoriteDTO;
	}

	@Override
	public List<CardDTO> getAllUserFavorites(Long userId) {
		// get the entire lit of cards corresponding to this deck
		ArrayList<Favorites> favoritesList = favoritesRepository.getAllUserFavorites(userId);
		// new, empty list of CardDTOs
		List<CardDTO> returnList = new ArrayList<>();

		// for every favorite card in the list, populate a DTO and add it to the DTO list
		for (Favorites favorites : favoritesList) {
			CardDTO returnCardDTO  = new CardDTO();
			BeanUtils.copyProperties(favorites.getId().getCard(), returnCardDTO);
			returnList.add(returnCardDTO);
		}
		return returnList;
	}

	@Override
	@Transactional
	public void deleteOneUserFavorite(Long userId, Long cardId) {
		favoritesRepository.deleteOneUserFavorite(userId, cardId);
	}

	@Override
	@Transactional
	public void deleteAllUserFavorites(Long userId) {
		favoritesRepository.deleteAllUserFavorites(userId);
	}


}
