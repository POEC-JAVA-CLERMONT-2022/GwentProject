package com.example.gwent_projet.models;

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
