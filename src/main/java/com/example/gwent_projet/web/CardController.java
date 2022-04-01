package com.example.gwent_projet.web;


import com.example.gwent_projet.entity.*;
import com.example.gwent_projet.repository.CardRepository;
import com.example.gwent_projet.services.CardService;
import com.example.gwent_projet.services.dto.CardDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

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
        }
    }


    /*// Get card by id
    @GetMapping("/cards/{id}")
    public ResponseEntity<Card> getCardById(@PathVariable("id") long id) {
        Optional<Card> cardData = cardRepository.findById(id);
        return cardData.map(card -> new ResponseEntity<>
                (card, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }*/
    // Get card by id
    @GetMapping("/cards/{id}")
    public ResponseEntity<CardDTO> getCardById(@PathVariable("id") long id) {
        try {
            CardDTO findCard = cardService.getCardById(id);
            return new ResponseEntity<>(findCard, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    /*@PostMapping("/cards")
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
    }*/
    @PostMapping("/cards")
    public ResponseEntity<CardDTO> createCard(@RequestBody Card card) {
        try {
            // create card in repository with data provided by method
            CardDTO newCard = cardService.saveCard(card);
            // card to be returned (TEMP)
            // then return a response entity with this card, and the CREATED HTTP status
            return new ResponseEntity<>(newCard, HttpStatus.CREATED);
        } catch (Exception e) {
            // return null value with ERROR HTTP status
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


   /* @PutMapping("/cards/{id}")
    public ResponseEntity<Object> updateCard(@RequestBody Card card, @PathVariable long id) {

        Optional<Card> cardOptional = cardRepository.findById(id);
        if (cardOptional.isEmpty())
            return ResponseEntity.notFound().build();
        card.setId(id);
        //cardRepository.save(card);
        cardService.updateCard(card);
        return ResponseEntity.noContent().build();
    }
    */
    @PutMapping("/cards/{id}")
    public ResponseEntity<CardDTO> updateCardById( @PathVariable("id") Long id, @RequestBody Card card) {
        try {
            CardDTO updatedCard = cardService.updateCard(id, card);
            return new ResponseEntity<>(updatedCard, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
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
            e.printStackTrace();
            return ResponseEntity.status(400).build();
        }
    }

    // Get card by card deck id
    @GetMapping("/cards/card-deck/{id}")
    public ResponseEntity<List<Card>> findAllByCardDeckId(@PathVariable("id") long id) {
        //List<Card> cards = new ArrayList<Card>(cardRepository.findAllByCardDeckId(id));
        try {
            List<Card> cards = new ArrayList<Card>();
            if (id > 0)
                cards.addAll(cardService.findCardsByCardDeck(id));
                if (cards.isEmpty()) {
                    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
                }
            return new ResponseEntity<>(cards, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(400).build();
        }
    }

}
