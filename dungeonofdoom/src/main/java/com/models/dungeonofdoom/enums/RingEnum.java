package com.models.dungeonofdoom.enums;

import com.models.dungeonofdoom.Items.ItemEffect;
import com.models.dungeonofdoom.Items.Potion.NoEffect;

public enum RingEnum {
    SUSTAIN_STRENGTH('0', "Ring of Sustain Strength", new NoEffect());



    private final ItemEffect effect;
    private final char symbol;
    private final String name;

    RingEnum( char symbol, String name, ItemEffect effect) {
        this.symbol = symbol;
        this.effect = effect;
        this.name = name;
    }

    public char getSymbol() {
        return symbol;
    }

    public String getName(){
        return this.name;
    }

    public ItemEffect getEffect() {
        return effect;
    }
}
