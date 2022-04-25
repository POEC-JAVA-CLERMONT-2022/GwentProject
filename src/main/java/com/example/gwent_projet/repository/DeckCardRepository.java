package com.example.gwent_projet.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.gwent_projet.entity.deck.DeckCard;
import com.example.gwent_projet.entity.pk.DeckCardPK;

@Repository
public interface DeckCardRepository extends JpaRepository<DeckCard, DeckCardPK> {

	 @Query(value="SELECT DC.card_id, DC.deck_id FROM deck_card AS DC WHERE DC.deck_id = :id", nativeQuery=true)
	 ArrayList<DeckCard> getAllCardsInDeck(@Param("id") Long deckId);
	 
	 @Query(value="DELETE FROM deck_card AS DC WHERE DC.deck_id = :id1 AND DC.card_id = :id2", nativeQuery=true)
	 void deleteOneCardFromDeck(@Param("id1") Long deckId, @Param("id2") Long cardId);
	 
	 @Query(value="DELETE FROM deck_card AS DC WHERE DC.deck_id = :id", nativeQuery=true)
	 void deleteAllCardsFromDeck(@Param("id") Long deckId);
	 
}
