package com.models.dungeonofdoom.enums;

import com.models.dungeonofdoom.Helper.Pair;

public enum WeaponEnum {

    MACE("Mace", 'M', "S",
     new Pair<>(2, 4), new Pair<>(1, 3), null),

    LONGSWORD("Longsword", 'L', "S", 
    new Pair<>(3, 4), new Pair<>(1, 2), null),

    // BOW("Bow", 'B', "S",
    //  new Pair<>(1, 1), new Pair<>(1, 1), null),

    // ARROW("Arrow", 'A', "B",
    //  new Pair<>(1, 1), new Pair<>(2, 3), "Bow"),

    DAGGER("Dagger", 'D', "S",
     new Pair<>(1, 6), null, null),

    TWO_HANDED_SWORD("Two-Handed Sword", 'T', "S",
     new Pair<>(4, 4), new Pair<>(1, 2), null),

    // DART("Dart", 'd', "B",
    //  new Pair<>(1, 1), null, null),

    // CROSSBOW("Crossbow", 'C', "S",
    //  new Pair<>(1, 1), new Pair<>(1, 1), null),

    // CROSSBOW_BOLT("Crossbow Bolt", 'c', "B",
    //  new Pair<>(1, 2), new Pair<>(2, 5), "Crossbow"),

    SPEAR("Spear", 'S', "S",
     new Pair<>(2, 3), new Pair<>(1, 6), null);

    private final String name;
    private final char symbol;
    private final String type;
    private final Pair<Integer, Integer> damageWielded;
    private final Pair<Integer, Integer> damageThrown;
    private final String requiredWeapon;

    WeaponEnum(String name, char symbol, String type, Pair<Integer, Integer> damageWielded, Pair<Integer, Integer> damageThrown, String requiredWeapon) {
        this.name = name;
        this.symbol = symbol;
        this.type = type;
        this.damageWielded = damageWielded;
        this.damageThrown = damageThrown;
        this.requiredWeapon = requiredWeapon;
    }

    public String getName() {
        return name;
    }

    public char getSymbol() {
        return symbol;
    }

    public String getType() {
        return type;
    }

    public Pair<Integer, Integer> getDamageWielded() {
        return damageWielded;
    }

    public Pair<Integer, Integer> getDamageThrown() {
        return damageThrown;
    }

    public String getRequiredWeapon() {
        return requiredWeapon;
    }
}
