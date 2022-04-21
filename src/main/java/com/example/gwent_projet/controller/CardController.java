package com.example.gwent_projet.controller;


import com.example.gwent_projet.entity.card.Card;
import com.example.gwent_projet.repository.CardRepository;
import com.example.gwent_projet.services.CardService;
import com.example.gwent_projet.services.dto.card.CardDTO;
import com.example.gwent_projet.services.dto.card.CreateCardDTO;
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

@CrossOrigin(origins = "http://localhost:4200")
@RestController
//TODO: requestmapping
public class CardController {

    @Autowired
    CardRepository cardRepository;
    @Autowired
    CardService cardService;


    // Get all cards
    @GetMapping("/cards")
    public ResponseEntity<List<CardDTO>> getAllCards(@RequestParam(required = false) Long id) {
        try {
            List<CardDTO> cards = new ArrayList<CardDTO>();
            if (id == null)
                cards = cardService.getAllCards();
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
    public ResponseEntity<CreateCardDTO> getCardById(@PathVariable("id") long id) {
        try {
            CreateCardDTO findCard = cardService.getCardById(id);
            return new ResponseEntity<>(findCard, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            //TODO: utiliser un logger
            //TODO: ResponseEntity.internalServerError();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    @PostMapping("/cards")
    public ResponseEntity<CardDTO> createCard(@RequestBody CreateCardDTO createCardDTO) {
        try {
            CardDTO newCard = cardService.createCard(createCardDTO);
            return new ResponseEntity<>(newCard, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PutMapping("/cards/{id}")
    public ResponseEntity<CardDTO> updateCardById( @PathVariable("id") Long id, @RequestBody CreateCardDTO card) {
        try {
            CardDTO updatedCard = cardService.updateCard(id, card);
            //TODO: ResponseEntity.created();
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
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
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
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Get cards by name LIKE
    @GetMapping("/cards/card-name/{name}")
    public ResponseEntity<List<Card>> findCardsByName( String name) {
        try {
            List<Card> cards = new ArrayList<Card>();
            if (name != null)
                cards.addAll(cardService.findCardsByName(name));
            if (cards.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(cards, HttpStatus.OK);
        } catch (Exception e) {
            //return ResponseEntity.status(400).build();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
