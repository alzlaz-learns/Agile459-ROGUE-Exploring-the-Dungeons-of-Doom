package com.models.dungeonofdoom.enums;

import com.models.dungeonofdoom.Items.Potion.*;


public enum PotionEnum {
    
    BLINDNESS(new Blindness()),
    CONFUSION(new Confusion()),
    EXTRA_HEALING(new ExtraHealing()),
    GAIN_STRENGTH(new GainStrength()),
    HASTE_SELF(new Haste()),
    HEALING(new Healing()),
    MAGIC_DETECTION,
    MONSTER_DETECTION,
    PARALYSIS,
    RAISE_LEVEL,
    RESTORE_STRENGTH,
    SEE_INVISIBLE,
    THIRST_QUENCHING(new ThirstQuenching()),
    OTHER_POTION(new OtherPotion())
    ;

    private final PotionEffect effect;

    PotionEnum(PotionEffect effect) {
        this.effect = effect;
    }
    
    PotionEnum() {
        this.effect = new NoEffect(); 
    }

    public PotionEffect getEffect() {
        return effect;
    }
}
