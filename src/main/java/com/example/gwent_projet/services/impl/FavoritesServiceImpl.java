package com.example.gwent_projet.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.gwent_projet.entity.card.Card;
import com.example.gwent_projet.entity.favorites.Favorites;
import com.example.gwent_projet.entity.pk.UserCardPK;
import com.example.gwent_projet.entity.user.User;
import com.example.gwent_projet.repository.CardRepository;
import com.example.gwent_projet.repository.FavoritesRepository;
import com.example.gwent_projet.repository.UserRepository;
import com.example.gwent_projet.services.FavoritesService;
import com.example.gwent_projet.services.dto.card.CardDTO;
import com.example.gwent_projet.services.dto.favorites.FavoritesDTO;
import com.example.gwent_projet.services.dto.user.UserDTO;

@Service
public class FavoritesServiceImpl implements FavoritesService {

	private UserRepository userRepository;
	private CardRepository cardRepository;
	private FavoritesRepository favoritesRepository;

	public FavoritesServiceImpl (UserRepository userRepository, CardRepository cardRepository, FavoritesRepository favoritesRepository) {
		super();
		this.userRepository = userRepository;
		this.cardRepository = cardRepository;
		this.favoritesRepository = favoritesRepository;
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
