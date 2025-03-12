package com.models.dungeonofdoom.enums;



import com.models.dungeonofdoom.Items.ItemEffect;
import com.models.dungeonofdoom.Items.Scroll.*;

public enum ScrollEnum {
    CONFUSE_MONSTER(' ', "Scroll of Confusion",new ConfusionScroll()),
    ENCHANT_ARMOR(' ',  "Scroll of Enchant Armor",new EnchantArmor()),
    HOLD_MONSTER(' ', "Scroll of Hold Monster",new HoldMonster()),
    SLEEP(' ', "Scroll of Sleep",new SleepScroll()),
    CREATE_MONSTER(' ', "Scroll of Create Monster",new CreateMonster()),
    IDENTIFY(' ',"Scroll of Identify", new NoEffect()),
    MAGIC_MAPPING(' ',  "Scroll of Magic Mapping",new MagicMapping()),
    FOOD_DETECTION(' ', "Scroll of Food Detection", new NoEffect()),
    TELEPORTATION(' ', "Scroll of Teleportation", new TeleportationScroll()),
    REMOVE_CURSE(' ', "Scroll of Remove Curse", new NoEffect()),
    ENCHANT_WEAPON(' ', "Scroll of Enchant Weapon", new NoEffect()),
    SCARE_MONSTER(' ', "Scroll of Scare Monster", new NoEffect()),
    NOTHING(' ', "Scroll of Nothing", new NoEffect()),
    VORPRAL_ENCHANT(' ', "Scroll of Vorpral Enchant", new NoEffect()),
    AGGRAVATE_MONSTER(' ', "Scroll of Aggravate Monster", new AggravateMonster());
    ;

    private final ItemEffect effect;
    private final char symbol;
    private final String name;

    ScrollEnum(char symbol,String name, ItemEffect effect){
        this.symbol = symbol;
        this.effect = effect;
        this.name = name;
    }

    ScrollEnum(){
        this.symbol = ' ';
        this.name = "No effect";
        this.effect = new NoEffect(); 
    }

    public ItemEffect getEffect() {
        return effect;
    }

    public char getSymbol() {
        return symbol;
    }
    public String getName(){
        return this.name;
    }

    
}
