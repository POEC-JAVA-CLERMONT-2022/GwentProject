package com.example.gwent_projet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.gwent_projet.web.UserService;
import com.example.gwent_utils.consoleDisplay;

@SpringBootApplication
public class GwentProjetApplication {

    public static void main(String[] args) {
        SpringApplication.run(GwentProjetApplication.class, args);
        
		UserService userService = new UserService();
		consoleDisplay consoleDisplay = new consoleDisplay();
		
		// ------------------------------------------------------------------
		/* temporary console display for user handling
		 * feel free to delete once integrated into framework 
		 */
		// execute method "run" that prints the menu and handles actions per user choice
		userService.run();
		// line break because Java sucks at handling line breaks
		consoleDisplay.consoleLineBreak();
		
    }

}
