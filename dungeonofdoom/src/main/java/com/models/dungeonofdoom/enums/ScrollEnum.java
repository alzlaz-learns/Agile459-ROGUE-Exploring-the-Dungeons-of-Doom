package com.models.dungeonofdoom.enums;



import com.models.dungeonofdoom.Items.ItemEffect;
import com.models.dungeonofdoom.Items.Scroll.NoEffect;

public enum ScrollEnum {
    CONFUSE_MONSTER(),
    ;

    private final ItemEffect effect;

    ScrollEnum(ItemEffect effect){
        this.effect = effect;
    }

    ScrollEnum(){
        this.effect = new NoEffect(); 
    }

    public ItemEffect getEffect() {
        return effect;
    }
}
