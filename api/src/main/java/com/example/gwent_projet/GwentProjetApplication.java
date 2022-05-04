package com.example.gwent_projet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;


@SpringBootApplication
public class GwentProjetApplication {

	public static void main(String[] args) {
		SpringApplication.run(GwentProjetApplication.class, args);

	}

	@EventListener(classes = {ApplicationStartedEvent.class})
	public void applicationStarted() {
		System.out.println("GwentApp is started !");
	}
}