package com.models.dungeonofdoom.enums;

import com.models.dungeonofdoom.Items.Potion.Blindness;
import com.models.dungeonofdoom.Items.Potion.NoEffect;
import com.models.dungeonofdoom.Items.Potion.PotionEffect;

public enum PotionEnum {
    
    BLINDNESS(new Blindness()),
    CONFUSION,
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
        this.effect = new NoEffect(); // Or any default you choose
    }

    public PotionEffect getEffect() {
        return effect;
    }
}
