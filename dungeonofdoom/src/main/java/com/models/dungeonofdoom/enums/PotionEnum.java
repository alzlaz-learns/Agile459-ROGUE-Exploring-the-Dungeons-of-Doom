package com.models.dungeonofdoom.enums;

import com.models.dungeonofdoom.Items.ItemEffect;
import com.models.dungeonofdoom.Items.Potion.*;


public enum PotionEnum {
    
    BLINDNESS('¡',"Potion of Blindness",new Blindness()),
    CONFUSION('?',"Potion of Confusion",new Confusion()),
    EXTRA_HEALING('┴',"Potion of Extra Healing",new ExtraHealing()),
    GAIN_STRENGTH('┼',"Potion of Gain Strength",new GainStrength()),
    HASTE_SELF('±',"Potion of Haste",new Haste()),
    HEALING('+',"Potion of Healing",new Healing()),
    POISON('ƒ',"Potion of Poison",new Poison()),
    MAGIC_DETECTION('ƒ',"Potion of Magic Detection",new MagicDetection()),
    MONSTER_DETECTION('Ÿ',"Potion of Monster Detection",new MonsterDetection()),
    PARALYSIS('÷',"Potion of Paralysis",new Paralysis()),
    RAISE_LEVEL('¥',"Potion of Raise Level",new RaiseLevel()),
    RESTORE_STRENGTH('§',"Potion of Restore Strength",new NoEffect()),
    SEE_INVISIBLE('©',"Potion of See Invisible",new NoEffect()),
    THIRST_QUENCHING('~',"Potion of Thirst Quenching",new ThirstQuenching()),
    OTHER_POTION('┐',"Potion of Mystery",new OtherPotion())
    ;

    private final ItemEffect effect;
    private final char symbol;
    private final String name;

    PotionEnum( char symbol, String name, ItemEffect effect) {
        this.symbol = symbol;
        this.effect = effect;
        this.name = name;
    }
    
    PotionEnum() {
        this.symbol = '0';
        this.name = "No effect";
        this.effect = new NoEffect(); 
    }

    public char getSymbol() {
        return symbol;
    }

    public ItemEffect getEffect() {
        return effect;
    }

    public String getName(){
        return this.name;
    }
}
