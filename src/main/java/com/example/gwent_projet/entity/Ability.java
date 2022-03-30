package com.example.gwent_projet.entity;

import io.swagger.v3.oas.annotations.media.Schema;

//@Schema(type = "String", allowableValues = {"Berserker", "Commander", "Decoy", "Medic"})
@Schema(name = "Ability card values", description = "Ability card values")
public enum Ability {


    BERSERKER( "Berserker"),
    COMMANDER( "Commander"),
    DECOY( "Decoy"),
    MEDIC( "Medic"),
    MORALE_BOOST( "Morale boost"),
    MARDROEME("mardroeme"),
    MUSTER( "muster"),
    SCORCH( "scorch"),
    SPY( "spy"),
    TIGHT_BOND( "tightBond"),
    SUMMON_AVENGER( "summonAvenger");


    private final String abilityName;

    private Ability(String abilityName) {
        this.abilityName = abilityName;
    }

    public String getShortName() {
        return abilityName;
    }
}
