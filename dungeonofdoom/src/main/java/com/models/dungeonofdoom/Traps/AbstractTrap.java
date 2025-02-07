package com.models.dungeonofdoom.Traps;

import com.models.Player;
import com.models.dungeonofdoom.enums.TrapEffectEnum;
import com.models.dungeonofdoom.enums.TrapTypeEnum;

public abstract class AbstractTrap {
    private boolean hidden;
    private int x;
    private int y;
    protected final TrapTypeEnum trapType;

    //TODO: TrapTypeEnumPlayground to be changed when appropriate class is created from enum and after some testing.
    public AbstractTrap(boolean hidden, TrapTypeEnum trapType) {
        this.hidden = hidden;
        this.trapType = trapType;
    }

    //Only method that changes so we are switching to an abstract method instead of interfaces
    // public abstract void applyEffect(Player player);

    
    //refactoring
    //method for triggering when a Plater steps on it.
    // public String trigger(Player player) {
    //     this.hidden = false;
    //     applyEffect(player);
    //     return trapType.getMessage(); 
    // }

    public abstract String trigger(Player player);

    public boolean isHidden() {
        return hidden;
    }

    public void reveal() {
        this.hidden = false;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public TrapEffectEnum getEffect() {
        return trapType.getEffect(); 
    }

}
