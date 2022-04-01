package com.example.gwent_projet.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.gwent_projet.entity.Card;

public interface CardRepository extends JpaRepository<Card, Long> {

}
