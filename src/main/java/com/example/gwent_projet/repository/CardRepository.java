package com.example.gwent_projet.repository;

import com.example.gwent_projet.entity.card.Card;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CardRepository extends JpaRepository<Card, Long> {

    List<Card> findByCardDeck_Id(Long id);

    List<Card> findByNameContaining(String name);
}
