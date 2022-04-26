package com.example.gwent_projet.controller;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.gwent_projet.entity.card.CardDeck;
import com.example.gwent_projet.repository.CardDeckRepository;
import com.example.gwent_projet.services.CardDeckService;
import com.example.gwent_projet.services.dto.cardDeck.CardDeckDTO;
import com.example.gwent_projet.services.dto.cardDeck.CreateCardDeckDTO;

@RestController
public class CardDeckController {

    @Autowired
    CardDeckRepository cardDeckRepository;
    @Autowired
    CardDeckService cardDeckService;

    // Get all cardDeck
    @GetMapping("/cardDecks")
    public ResponseEntity<List<CardDeckDTO>> getAllCardDeck(@RequestParam(required = false) Long id) {
        try {
            List<CardDeckDTO> cardDecks = new ArrayList<CardDeckDTO>();
            if (id == null)
                cardDecks = cardDeckService.getAllCardDecks();
            if (cardDecks.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(cardDecks, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(400).build();
        }
    }


    // Get CD by id
    @GetMapping("/cardDecks/{id}")
    public ResponseEntity<CreateCardDeckDTO> getCardDeckById(@PathVariable("id") long id) {
        try {
            CreateCardDeckDTO findCardDeck = cardDeckService.getCardDeckById(id);
            return new ResponseEntity<>(findCardDeck, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/cardDecks")
    public ResponseEntity<CardDeckDTO> createCardDeck(@RequestBody CreateCardDeckDTO createCardDeckDTO) {
        try {
            CardDeckDTO newCardDeck = cardDeckService.createCardDeck(createCardDeckDTO);
            return new ResponseEntity<>(newCardDeck, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PutMapping("/cardDecks/{id}")
    public ResponseEntity<CardDeckDTO> updateCardDeckById(@PathVariable("id") Long id, @RequestBody CreateCardDeckDTO cardDeck) {

        try {
            CardDeckDTO updatedCardDeck = cardDeckService.updateCardDeck(id, cardDeck);
            return new ResponseEntity<>(updatedCardDeck, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    // Delete by id
    @DeleteMapping("/cardDecks/{id}")
    public ResponseEntity<CardDeck> deleteCardDeck(@RequestBody @PathVariable("id") Long id) {
        try {
            if (id != null){
                cardDeckService.deleteCardDeckById(id);
            }
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(400).build();
        }
    }
}
