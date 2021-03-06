package com.example.gwent_projet.entity.card;

public enum Type {

    UNIT("Unit"),
    LEADER("Leader"),
    HERO("Hero"),
    SPECIAL("Special");

    private final String typeName;

    Type(String typeName) {
        this.typeName = typeName;
    }

    public String getTypeName() {
        return typeName;
    }
}