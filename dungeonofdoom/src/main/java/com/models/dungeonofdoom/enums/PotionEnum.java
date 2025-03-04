package com.models.dungeonofdoom.enums;

import com.models.dungeonofdoom.Items.ItemEffect;
import com.models.dungeonofdoom.Items.Potion.*;


public enum PotionEnum {
    
    BLINDNESS(new Blindness()),
    CONFUSION(new Confusion()),
    EXTRA_HEALING(new ExtraHealing()),
    GAIN_STRENGTH(new GainStrength()),
    HASTE_SELF(new Haste()),
    HEALING(new Healing()),
    MAGIC_DETECTION(new MagicDetection()),
    MONSTER_DETECTION(new MonsterDetection()),
    PARALYSIS,
    RAISE_LEVEL,
    RESTORE_STRENGTH,
    SEE_INVISIBLE,
    THIRST_QUENCHING(new ThirstQuenching()),
    OTHER_POTION(new OtherPotion())
    ;

    private final ItemEffect effect;

    PotionEnum(ItemEffect effect) {
        this.effect = effect;
    }
    
    PotionEnum() {
        this.effect = new NoEffect(); 
    }

    public ItemEffect getEffect() {
        return effect;
    }
}
