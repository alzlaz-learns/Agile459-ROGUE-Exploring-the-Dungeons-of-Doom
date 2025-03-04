package com.models.dungeonofdoom.enums;

import com.models.dungeonofdoom.Items.ItemEffect;
import com.models.dungeonofdoom.Items.Potion.*;


public enum PotionEnum {
    
    BLINDNESS('¡',new Blindness()),
    CONFUSION('?',new Confusion()),
    EXTRA_HEALING('┴',new ExtraHealing()),
    GAIN_STRENGTH('┼',new GainStrength()),
    HASTE_SELF('±',new Haste()),
    HEALING('+',new Healing()),
    MAGIC_DETECTION('ƒ',new MagicDetection()),
    MONSTER_DETECTION('Ÿ',new MonsterDetection()),
    PARALYSIS('÷',new Paralysis()),
    RAISE_LEVEL('¥',new RaiseLevel()),
    RESTORE_STRENGTH('§',null),
    SEE_INVISIBLE('©',null),
    THIRST_QUENCHING('~',new ThirstQuenching()),
    OTHER_POTION('┐',new OtherPotion())
    ;

    private final ItemEffect effect;
    private final char symbol;

    PotionEnum(char symbol, ItemEffect effect) {
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

    public ItemEffect getEffect() {
        return effect;
    }
}
