package com.example.gwent_projet.services.dto;

public class CardDeckDTO {

    private Long id;

    private String name;

    public CardDeckDTO() {
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
