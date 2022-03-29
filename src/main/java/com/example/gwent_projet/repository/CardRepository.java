package com.example.gwent_projet.repository;

import com.example.gwent_projet.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<Card, Long> {

}
