package com.example.gwent_projet.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.gwent_projet.entity.favorites.Favorites;
import com.example.gwent_projet.entity.pk.UserCardPK;

@Repository
public interface FavoritesRepository extends JpaRepository<Favorites, UserCardPK>  {

	@Query(value="SELECT F.card_id, F.user_id FROM favorites AS F WHERE F.user_id = :id", nativeQuery=true)
	 ArrayList<Favorites> getAllUserFavorites(@Param("id") Long userId);
	 
	 @Query(value="DELETE FROM favorites WHERE user_id = :id1 AND card_id = :id2", nativeQuery=true)
	 @Modifying
	 void deleteOneUserFavorite(@Param("id1") Long userId, @Param("id2") Long cardId);
	 
	 @Query(value="DELETE FROM favorites WHERE user_id = :id", nativeQuery=true)
	 @Modifying
	 void deleteAllUserFavorites(@Param("id") Long userId);
	 
	
}
