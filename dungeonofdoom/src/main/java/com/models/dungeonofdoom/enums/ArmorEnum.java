package com.models.dungeonofdoom.enums;

public enum ArmorEnum {


    LEATHER("Leather", 8),
    STUDDED_LEATHER("Studded Leather", 7),
    RING_MAIL("Ring Mail", 7),
    SCALE_MAIL("Scale Mail", 6),
    CHAIN_MAIL("Chain Mail", 5),
    BANDED_MAIL("Banded Mail", 4),
    SPLINT_MAIL("Splint Mail", 4),
    PLATE_MAIL("Plate Mail", 3);

    private final String name;
    private final int armorClass;

    private ArmorEnum(String name, int armorClass){
        this.name = name;
        this.armorClass = armorClass;
    }

    public String getName() { return name; }
    public int getArmorClass() { return armorClass; }
    
}
