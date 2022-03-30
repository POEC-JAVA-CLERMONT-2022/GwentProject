package com.example.gwent_projet.entity;

import javax.persistence.*;
import java.util.Objects;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table (name = "card")
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Size(min=2, max=10)
    private String name;

    @NotNull
    @Size(min=2, max=10)
    private String picture;

    @NotNull
    @Min(1)
    @Column(name = "power_lvl")
    private Integer powerLvl;

    @NotNull
    @Size(min=10, max=50)
    private String description;

    @NotNull
    @Size(min=5, max=20)
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

    public long getId() {
        return id;
    }

    public CardDeck getCardDeck() {
        return cardDeck;
    }

    public Ability getAbility() {
        return ability;
    }

    public Row getRowName() {
        return rowName;
    }

    public Type getType() {
        return type;
    }
}
