package com.example.gwent_projet.models;

import javax.persistence.*;

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


    @Enumerated(EnumType.STRING)
    private Ability ability;

    @Enumerated(EnumType.STRING)
    private Row rowName;

    @Enumerated(EnumType.STRING)
    private Type type;


    public Card(String name, String picture, Integer powerLvl, String description,
                String location, Ability ability, Row rowName, Type type) {
        this.name = name;
        this.picture = picture;
        this.powerLvl = powerLvl;
        this.description = description;
        this.location = location;
        this.ability = ability;
        this.rowName = rowName;
        this.type = type;
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
