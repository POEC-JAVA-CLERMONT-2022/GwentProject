package com.example.gwent_projet.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table (name = "card")
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private String name;
    private String picture;

    @Column(name = "power_lvl")
    private Integer powerLvl;

    private String description;
    private String location;

    @JoinColumn(name = "cardDeck_id")
    @ManyToOne
    private CardDeck cardDeck;


    @Enumerated(EnumType.STRING)
    private Ability ability;

    @Enumerated(EnumType.STRING)
    private Row rowName;

    @Enumerated(EnumType.STRING)
    private Type type;


    public Card(String name, String picture, Integer powerLvl, String description,
                String location, Ability ability, Row rowName, Type type, CardDeck cardDeck) {
        this.name = name;
        this.picture = picture;
        this.powerLvl = powerLvl;
        this.description = description;
        this.location = location;
        this.ability = ability;
        this.rowName = rowName;
        this.type = type;
        this.cardDeck = cardDeck;
    }

    public Card() {
    }

    @Override
    public String toString() {
        return "Card{" +
                "name='" + name + '\'' +
                ", picture='" + picture + '\'' +
                ", powerLvl=" + powerLvl +
                ", description='" + description + '\'' +
                ", location='" + location + '\'' +
                ", ability=" + ability +
                ", row=" + rowName +
                ", type=" + type +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return Objects.equals(id, card.id) && Objects.equals(name, card.name) && Objects.equals(picture, card.picture) && Objects.equals(powerLvl, card.powerLvl) && Objects.equals(description, card.description) && Objects.equals(location, card.location) && Objects.equals(cardDeck, card.cardDeck) && ability == card.ability && rowName == card.rowName && type == card.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, picture, powerLvl, description, location, cardDeck, ability, rowName, type);
    }



    //getter setter
    public String getName() {
        return name;
    }

    public String getPicture() {
        return picture;
    }

    public Integer getPowerLvl() {
        return powerLvl;
    }

    public String getDescription() {
        return description;
    }

    public String getLocation() {
        return location;
    }

    public Long getId() {
        return id;
    }
}
