package com.example.gwent_projet.services.dto.cardDeck;

public class CreateCardDeckDTO {

    private String name;

    public CreateCardDeckDTO(String name) {
        this.name = name;
    }

    public CreateCardDeckDTO() {

    }

    @Override
    public String toString() {
        return "CreateCardDeckDTO{" +
                "name='" + name + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
