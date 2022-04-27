package com.example.gwent_projet.entity.card;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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

    public void setName(String name) {
        this.name = name;
    }
}