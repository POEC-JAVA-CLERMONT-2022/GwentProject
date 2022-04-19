package com.example.gwent_projet;

import com.example.gwent_projet.cli.UserCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;


@SpringBootApplication
public class GwentProjetApplication {

	@Autowired
	private UserCommand userCommand;

	public static void main(String[] args) {
		SpringApplication.run(GwentProjetApplication.class, args);

	}

	@EventListener(classes = {ApplicationStartedEvent.class})
	public void applicationStarted() {
		userCommand.printTitle("GwentApp is started !");
		// ------------------------------------------------------------------
		/* temporary console display for user handling
		 * feel free to delete
		 */
		// this.userCommand.run();
	}


}