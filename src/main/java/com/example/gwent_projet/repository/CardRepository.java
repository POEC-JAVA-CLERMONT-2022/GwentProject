package com.example.gwent_projet.repository;

import com.example.gwent_projet.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface CardRepository extends JpaRepository<Card, Long> {

    List<Card> findByCardDeck_Id(Long id);

    List<Card> findByNameContaining(String name);
}
