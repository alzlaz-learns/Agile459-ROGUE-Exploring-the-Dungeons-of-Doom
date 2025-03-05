package com.models.dungeonofdoom.enums;



import com.models.dungeonofdoom.Items.ItemEffect;
import com.models.dungeonofdoom.Items.Scroll.*;

public enum ScrollEnum {
    CONFUSE_MONSTER(' ', new ConfusionScroll()),
    ENCHANT_ARMOR(' '),
    HOLD_MONSTER(' '),
    SLEEP(' '),
    CREATE_MONSTER(' '),
    IDENTIFY(' '),
    MAGIC_MAPPING(' '),
    FOOD_DETECTION(' '),
    TELEPORTATION(' '),
    REMOVE_CURSE(' '),
    ENCHANT_WEAPON(' '),
    SCARE_MONSTER(' '),
    NOTHING(' '),
    VORPRAL_ENCHANT(' '),
    ;

    private final ItemEffect effect;
    private final char symbol;

    ScrollEnum(char symbol, ItemEffect effect){
        this.symbol = symbol;
        this.effect = effect;
    }

    ScrollEnum(char symbol){
        this.symbol = symbol;
        this.effect = new NoEffect(); 
    }

    public ItemEffect getEffect() {
        return effect;
    }
}
