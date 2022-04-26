package com.example.gwent_projet.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.gwent_projet.entity.card.CardDeck;

public interface CardDeckRepository extends JpaRepository<CardDeck, Long> {


}
