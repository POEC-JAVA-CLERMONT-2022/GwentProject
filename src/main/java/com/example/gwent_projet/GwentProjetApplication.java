package com.example.gwent_projet;

import com.example.gwent_projet.entity.Card;
import com.example.gwent_projet.services.CardService;
import com.example.gwent_projet.services.dto.CardDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import java.util.List;
import com.example.gwent_projet.services.UserService;


@SpringBootApplication
public class GwentProjetApplication {

    @Autowired
    private CardService cardService;

    public static void main(String[] args) {
        SpringApplication.run(GwentProjetApplication.class, args);
        
		UserService userService = new UserService();
		
		// ------------------------------------------------------------------
		/* temporary console display for user handling
		 * feel free to delete once integrated into framework 
		 */
		// execute method "run" that prints the menu and handles actions per user choice
		userService.run();
		// line break because Java sucks at handling line breaks
    }

    @EventListener(classes = {ApplicationStartedEvent.class})
    public void applicationStarted() {
        System.out.println("--------------------");
        System.out.println("GwentApp is started !");
        System.out.println("--------------------");

        List<CardDTO> cards = cardService.getAllCards();
        System.out.println(cards);
    }
}
