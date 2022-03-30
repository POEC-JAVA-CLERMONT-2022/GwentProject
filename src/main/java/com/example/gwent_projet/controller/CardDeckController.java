package com.example.gwent_projet.controller;


import com.example.gwent_projet.entity.Card;
import com.example.gwent_projet.entity.CardDeck;
import com.example.gwent_projet.repository.CardDeckRepository;
import com.example.gwent_projet.services.CardDeckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class CardDeckController {

    @Autowired
    CardDeckRepository cardDeckRepository;
    @Autowired
    CardDeckService cardDeckService;

    // Get all cardDeck
    @GetMapping("/cardDecks")
    public ResponseEntity<List<CardDeck>> getAllCardDeck(@RequestParam(required = false) Long id) {
        try {
            List<CardDeck> cardDecks = new ArrayList<CardDeck>();
            if (id == null)
                cardDecks.addAll(cardDeckRepository.findAll());
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
    public ResponseEntity<CardDeck> getCardDeckById(@PathVariable("id") long id) {
        Optional<CardDeck> cardDeckData = cardDeckRepository.findById(id);
        return cardDeckData.map(card -> new ResponseEntity<>
                (card, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    @PostMapping("/cardDecks")
    public ResponseEntity<CardDeck> createCardDeck(@RequestBody CardDeck cardDeck) {
        try {
            if (cardDeck != null){
                cardDeckService.saveCardDeck(cardDeck);
            }
            return ResponseEntity.status(201).build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(400).build();
        }
    }


    @PutMapping("/cardDecks/{id}")
    public ResponseEntity<Object> updateCardDeck(@RequestBody CardDeck cardDeck, @PathVariable long id) {

        Optional<CardDeck> cardDeckOptional = cardDeckRepository.findById(id);
        if (cardDeckOptional.isEmpty())
            return ResponseEntity.notFound().build();
        cardDeck.setId(id);
        cardDeckRepository.save(cardDeck);
        return ResponseEntity.noContent().build();
    }

    // Delete by id
    @DeleteMapping("/cardDecks/{id}")
    public ResponseEntity<Card> deleteCardDeck(@RequestBody @PathVariable("id") Long id) {
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
