package com.example.gwent_projet;

import com.example.gwent_projet.models.Card;
import com.example.gwent_projet.services.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

@SpringBootApplication
public class GwentProjetApplication {

    @Autowired
    private CardService cardService;

    public static void main(String[] args) {
        SpringApplication.run(GwentProjetApplication.class, args);
    }

    @EventListener(classes = {ApplicationStartedEvent.class})
    public void applicationStarted() {
        System.out.println("GwentApp is started !");
        List<Card> cards = cardService.findAll();
    }
}
