package com.models.dungeonofdoom.enums;

public enum ArmorEnum {


    LEATHER("Leather", 'á', 8),
    STUDDED_LEATHER("Studded Leather", 'µ', 7),
    RING_MAIL("Ring Mail", 'ø', 7),
    SCALE_MAIL("Scale Mail", 'ú', 6),
    CHAIN_MAIL("Chain Mail", 'ï', 5),
    BANDED_MAIL("Banded Mail", 'é', 4),
    SPLINT_MAIL("Splint Mail", 'í', 4),
    PLATE_MAIL("Plate Mail", 'ó', 3);

    private final String name;
    private final char symbol;
    private final int armorClass;

    private ArmorEnum(String name, char symbol, int armorClass){
        this.name = name;
        this.symbol = symbol;
        this.armorClass = armorClass;
    }

    public String getName() { return name; }
    public int getArmorClass() { return armorClass; }
    public char getSymbol() { return symbol; }
}
