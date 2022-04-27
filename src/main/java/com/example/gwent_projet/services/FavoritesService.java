package com.example.gwent_projet.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.gwent_projet.services.dto.card.CardDTO;
import com.example.gwent_projet.services.dto.favorites.FavoritesDTO;

@Service
public interface FavoritesService {

	FavoritesDTO addFavorite(Long userId, Long cardId);
	
	List<CardDTO> getAllUserFavorites(Long userId);
	
	void deleteOneUserFavorite(Long userId, Long cardId);
	
	void deleteAllUserFavorites(Long userId);
	
}
