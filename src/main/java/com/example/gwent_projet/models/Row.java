package com.example.gwent_projet.models;


public enum Row {

    CLOSE_COMBAT( "Close combat"),
    RANGED( "Ranged"),
    AGILE("Agile"),
    SIEGE("Siege");

    private final String rowName;

    Row(String rowName) {
        this.rowName = rowName;
    }

    public String getRowName() {
        return rowName;
    }
}

