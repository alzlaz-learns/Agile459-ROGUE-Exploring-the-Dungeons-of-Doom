package com.playground.alex;

import com.models.Player;

public class TeleportTrapPlayground implements TrapInterfacePlayground {


    private boolean hidden;
    private final TrapTypeEnumPlayground trapType;
    private int x; 
    private int y;

    public TeleportTrapPlayground(boolean hidden) {
        this.hidden = hidden;
        this.trapType = TrapTypeEnumPlayground.TELEPORT_TRAP;
    }

    @Override
    public void applyEffect(Player player) {
        
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
