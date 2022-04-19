package com.example.gwent_projet.entity;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "Ability card values", description = "Ability card values")
public enum Ability {


    BERSERKER( "Berserker"),
    COMMANDER( "Commander"),
    DECOY( "Decoy"),
    MEDIC( "Medic"),
    MORALE_BOOST( "Morale boost"),
    MARDROEME("Mardroeme"),
    MUSTER( "Muster"),
    SCORCH( "Scorch"),
    SPY( "Spy"),
    TIGHT_BOND( "TightBond"),
    SUMMON_AVENGER( "SummonAvenger");


    private final String abilityName;

    private Ability(String abilityName) {
        this.abilityName = abilityName;
    }

    public String getShortName() {
        return abilityName;
    }
}
