package com.example.gwent_projet.controller;


import com.example.gwent_projet.models.*;
import com.example.gwent_projet.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RestController
public class CardController {

    @Autowired
    CardRepository cardRepository;


    // Get all cards
    @GetMapping("/cards")
    public ResponseEntity<List<Card>> getAllCards(@RequestParam(required = false) Long id) {
        try {
            List<Card> cards = new ArrayList<Card>();
            if (id == null)
                //cardRepository.findAll().forEach(cards::add);
                cards.addAll(cardRepository.findAll());
            if (cards.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(cards, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Get card by id
    @GetMapping("/cards/{id}")
    public ResponseEntity<Card> getCardById(@PathVariable("id") long id) {
        Optional<Card> cardData = cardRepository.findById(id);
        return cardData.map(card -> new ResponseEntity<>
                (card, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Create card
    @PostMapping("/cards")
    public ResponseEntity<Card> createCard(@RequestBody Card card) {
        try {
            Card _card = cardRepository
                    .save(new Card(card.getName(), card.getPicture(), card.getPowerLvl(), card.getDescription(),
                            card.getLocation(), Ability.BERSERKER, Row.AGILE, Type.HERO, new CardDeck("zozo")));
            return new ResponseEntity<>(_card, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    // Delete by id
    @DeleteMapping("/cards/{id}")
    public ResponseEntity<HttpStatus> deleteCard(@PathVariable("id") long id) {
        try {
            cardRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
