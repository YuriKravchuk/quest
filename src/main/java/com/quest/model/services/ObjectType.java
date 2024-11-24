package com.quest.model.services;

public enum ObjectType {
    EMPTY("⬜️"),
    TREE("🌳"),
    CAVE("🗻"),
    PERSON("🧙‍♂️"),
    MONSTER("👹"),
    HERO1("\uD83E\uDDB8\u200D♂\uFE0F"),
    HERO2("👨‍🎤"),
    HERO3("🦸‍♀️"),
    HERO4("👩‍🎤"),
    HERO5("🧝‍♂️"),
    MUSHROOM("🍄‍🟫"),
    WATER("💦"),
    BERRY("🫐"),
    NUTS("🥜");


    private final String symbol;

    ObjectType(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }
}
