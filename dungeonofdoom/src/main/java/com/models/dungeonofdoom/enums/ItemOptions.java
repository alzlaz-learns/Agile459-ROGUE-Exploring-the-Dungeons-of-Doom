package com.models.dungeonofdoom.enums;

public enum ItemOptions {
    WEARABLE("Wear"),
    QUAFFABLE("Quaff"),
    READABLE("Read"),
    WIELD("Wield"),
    CONSUMABLE("Eat"),
    PUTTABLE("Put On"),
    ALL(" "),
    ;

    private final String command;

    private ItemOptions(String command){
        this.command = command;
    }

    public String getName(){return this.command;}
}
