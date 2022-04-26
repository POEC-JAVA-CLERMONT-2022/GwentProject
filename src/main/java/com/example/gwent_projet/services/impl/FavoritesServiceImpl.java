package com.example.gwent_projet.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.gwent_projet.entity.card.Card;
import com.example.gwent_projet.entity.favorites.Favorites;
import com.example.gwent_projet.entity.pk.UserCardPK;
import com.example.gwent_projet.entity.user.User;
import com.example.gwent_projet.repository.CardRepository;
import com.example.gwent_projet.repository.FavoritesRepository;
import com.example.gwent_projet.repository.UserRepository;
import com.example.gwent_projet.services.FavoritesService;
import com.example.gwent_projet.services.dto.deckCard.DeckCardCreationDTO;
import com.example.gwent_projet.services.dto.favorites.FavoritesDTO;

@Service
public class FavoritesServiceImpl implements FavoritesService {

	private UserRepository userRepository;
	private CardRepository cardRepository;
	private FavoritesRepository favoritesRepository;

	public FavoritesServiceImpl ( UserRepository userRepository, CardRepository cardRepository) {
		super();
		this.userRepository = userRepository;
		this.cardRepository = cardRepository;
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
		
		favoritesRepository.save(favorite);
		
		/*
		FavoritesDTO returnFavoriteDTO = new FavoritesDTO(
				favoritesRepository.findById(userCardPK).get().getId().getUser().getId(),
				favoritesRepository.findById(userCardPK).get().getId().getCard().getId()
				);
		return returnFavoriteDTO;
		*/
		
		return returnFavoriteDTO;
		
	}

	@Override
	public List<FavoritesDTO> getAllUserFavorites(Long userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteOneUserFavorite(Long userId, Long cardId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAllUserFavorites(Long userId) {
		// TODO Auto-generated method stub
		
	}

}
