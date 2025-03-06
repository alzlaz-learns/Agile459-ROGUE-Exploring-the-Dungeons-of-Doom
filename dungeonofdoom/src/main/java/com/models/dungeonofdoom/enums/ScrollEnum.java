package com.models.dungeonofdoom.enums;



import com.models.dungeonofdoom.Items.ItemEffect;
import com.models.dungeonofdoom.Items.Scroll.*;

public enum ScrollEnum {
    CONFUSE_MONSTER(' ', new ConfusionScroll()),
    ENCHANT_ARMOR(' ', new EnchantArmor()),
    HOLD_MONSTER(' ', new HoldMonster()),
    SLEEP(' ', new SleepScroll()),
    CREATE_MONSTER(' ', new CreateMonster()),
    IDENTIFY(' '),
    MAGIC_MAPPING(' ', new MagicMapping()),
    FOOD_DETECTION(' '),
    TELEPORTATION(' ', new TeleportationScroll()),
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

    public char getSymbol() {
        return symbol;
    }
}
