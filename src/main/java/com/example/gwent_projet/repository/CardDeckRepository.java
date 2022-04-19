package com.example.gwent_projet.repository;

import com.example.gwent_projet.entity.card.CardDeck;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardDeckRepository extends JpaRepository<CardDeck, Long> {


}
