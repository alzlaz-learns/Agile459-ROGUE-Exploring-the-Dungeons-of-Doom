package com.models.dungeonofdoom.enums;

import com.models.dungeonofdoom.Items.Potion.*;


public enum PotionEnum {
    
    BLINDNESS('¡',new Blindness()),
    CONFUSION('?',new Confusion()),
    EXTRA_HEALING('┴',new ExtraHealing()),
    GAIN_STRENGTH('┼',new GainStrength()),
    HASTE_SELF('±',null),
    HEALING('+',null),
    MAGIC_DETECTION('ƒ',null),
    MONSTER_DETECTION('Ÿ',null),
    PARALYSIS('÷',null),
    RAISE_LEVEL('¥',null),
    RESTORE_STRENGTH('§',null),
    SEE_INVISIBLE('©',null),
    THIRST_QUENCHING('~',null),
    OTHER_POTION('┐',null)
    ;

    private final char symbol;
    private final PotionEffect effect;

    PotionEnum(char symbol, PotionEffect effect) {
        this.symbol = symbol;
        this.effect = effect;
    }
    
    PotionEnum(char symbol) {
        this.symbol = symbol;
        this.effect = new NoEffect(); 
    }

    public char getSymbol() {
        return symbol;
    }

    public PotionEffect getEffect() {
        return effect;
    }
}
