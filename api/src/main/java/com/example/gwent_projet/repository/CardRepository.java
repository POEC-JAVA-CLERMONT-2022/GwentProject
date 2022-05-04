package com.example.gwent_projet.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.gwent_projet.entity.card.Card;

public interface CardRepository extends JpaRepository<Card, Long> {

    List<Card> findByCardDeck_Id(Long id);

    List<Card> findByNameContaining(String name);
}
