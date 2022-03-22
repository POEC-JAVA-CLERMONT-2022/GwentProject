package com.example.gwent_projet.data;

import com.example.gwent_projet.services.CardService;

public class MainApplication {

    public static void main(String[] args) {
        CardService libraryService = new CardService();
        libraryService.runCard();
    }
}
