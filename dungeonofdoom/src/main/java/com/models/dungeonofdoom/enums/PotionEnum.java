package com.models.dungeonofdoom.enums;

import com.models.dungeonofdoom.Items.Potion.*;


public enum PotionEnum {
    
    BLINDNESS(new Blindness()),
    CONFUSION(new Confusion()),
    EXTRA_HEALING,
    GAIN_STRENGTH,
    HASTE_SELF,
    HEALING,
    MAGIC_DETECTION,
    MONSTER_DETECTION,
    PARALYSIS,
    RAISE_LEVEL,
    RESTORE_STRENGTH,
    SEE_INVISIBLE,
    THIRST_QUENCHING,
    OTHER_POTION
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
