package com.example.gwent_projet.web;

import com.example.gwent_projet.models.Ability;
import com.example.gwent_projet.models.Card;
import com.example.gwent_projet.models.Row;
import com.example.gwent_projet.models.Type;
import com.example.gwent_projet.services.CardService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class CardController {

    private CardService cardService = new CardService();

    /*@GetMapping("/cards")
    public ArrayList<Card> getCards() {
        return cardService.getAll();
    }

    @GetMapping("/cards/add")
    public String addCards() {
        cardService.add(new Card("Gerald", "geraldPic", 3, "Loup blanc",
                "Skelidge", Ability.COMMANDER, Row.CLOSE_COMBAT, Type.HERO));
        return "OK";
    }
     */

}
