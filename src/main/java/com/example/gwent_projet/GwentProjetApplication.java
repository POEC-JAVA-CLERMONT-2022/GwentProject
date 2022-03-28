package com.example.gwent_projet;

import com.example.gwent_projet.cli.UserCommand;
import com.example.gwent_projet.models.Card;
import com.example.gwent_projet.services.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;


import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;

import java.util.List;


@SpringBootApplication
public class GwentProjetApplication {

    @Autowired
    private CardService cardService;
    @Autowired
   private UserCommand userCommand;

    public static void main(String[] args) {
        SpringApplication.run(GwentProjetApplication.class, args);
        
    }

    @EventListener(classes = {ApplicationStartedEvent.class})
    public void applicationStarted() {
        System.out.println("--------------------");
        System.out.println("GwentApp is started !");
        System.out.println("--------------------");
        List<Card> cards = cardService.findAll();
     // ------------------------------------------------------------------
     		/* temporary console display for user handling
     		 * feel free to delete
     		 */
        this.userCommand.run();
    }
}
