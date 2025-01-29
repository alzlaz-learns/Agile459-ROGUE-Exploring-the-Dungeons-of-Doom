package com.playground.alex;

import com.models.Player;

public class TrapDoorPlayground implements TrapInterfacePlayground{
    // Each room may have zero or more traps
    // Each trap will be triggered whenever a player moves onto it
    // In some cases traps may be hidden.
    // The player will either discover the trap by triggering it or by using the “s (search)” command to reveal it.
    
    private boolean hidden;
    private final TrapTypeEnumPlayground trapType;
    private int x; 
    private int y;

    public TrapDoorPlayground(boolean hidden) {
        this.hidden = hidden;
        this.trapType = TrapTypeEnumPlayground.TRAP_DOOR;
    }

    @Override
    public String trigger(Player player) {
        this.hidden = false; // Reveal the trap when triggered
        return trapType.getMessage();
    }

    @Override
    public boolean isHidden() {
        return hidden;
    }

    @Override
    public void reveal() {
        this.hidden = false;
    }

    @Override
    public int getX() {
        return this.x;
    }

    @Override
    public int getY() {
        return this.y;
    }

    @Override
    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public TrapEffectEnumPlayground getEffect(){
        return this.trapType.getEffect();
    }

}
