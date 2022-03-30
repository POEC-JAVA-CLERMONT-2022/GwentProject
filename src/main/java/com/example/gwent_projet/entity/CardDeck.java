package com.example.gwent_projet.entity;

import javax.persistence.*;

@Entity
@Table(name = "card_deck")
public class CardDeck {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private String name;


    // Constructeur
    public CardDeck(String name) {
        this.name = name;
    }
    public CardDeck() {
    }

    //toString
    @Override
    public String toString() {
        return "CardDeck{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    // Getter
    public Long getId() {
        return id;
    }
    public String getName() {
        return name;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
