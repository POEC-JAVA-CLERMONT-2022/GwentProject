package com.example.gwent_projet.services.dto.cardDeck;

public class CardDeckDTO {

    private Long id;

    private String name;

    public CardDeckDTO() {
    }

    @Override
    public String toString() {
        return "CardDeckDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
