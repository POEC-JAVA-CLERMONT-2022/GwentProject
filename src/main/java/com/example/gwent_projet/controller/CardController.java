package com.example.gwent_projet.controller;


import com.example.gwent_projet.entity.*;
import com.example.gwent_projet.repository.CardRepository;
import com.example.gwent_projet.services.CardService;
import com.example.gwent_projet.services.impl.CardServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RestController
public class CardController {

    @Autowired
    CardRepository cardRepository;
    @Autowired
    CardService cardService;

    // Get all cards
    @GetMapping("/cards")
    public ResponseEntity<List<Card>> getAllCards(@RequestParam(required = false) Long id) {
        try {
            List<Card> cards = new ArrayList<Card>();
            if (id == null)
                cards.addAll(cardRepository.findAll());
            if (cards.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(cards, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(400).build();
            //catch (Exception e) {
            //return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    // Get card by id
    @GetMapping("/cards/{id}")
    public ResponseEntity<Card> getCardById(@PathVariable("id") long id) {
        Optional<Card> cardData = cardRepository.findById(id);
        return cardData.map(card -> new ResponseEntity<>
                (card, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    @PostMapping("/cards")
    public ResponseEntity<Card> createCard(@RequestBody Card card) {
        try {
            if (card != null){
                cardService.saveCard(card);
            }
            return ResponseEntity.status(201).build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(400).build();
        }
    }



    @PutMapping("/cards/{id}")
    public ResponseEntity<Object> updateCard(@RequestBody Card card, @PathVariable long id) {

        Optional<Card> cardOptional = cardRepository.findById(id);
        if (cardOptional.isEmpty())
            return ResponseEntity.notFound().build();
        card.setId(id);
        cardRepository.save(card);
        return ResponseEntity.noContent().build();
    }


    // Delete by id
    @DeleteMapping("/cards/{id}")
    public ResponseEntity<Card> deleteCard(@RequestBody @PathVariable("id") Long id) {
        try {
            if (id != null){
                cardService.deleteCardById(id);
            }
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(400).build();
        }
    }
}
