package com.playground.alex;

import java.util.Random;

import com.models.Player;

public class BearTrapPlayground implements TrapInterfacePlayground{

    private boolean hidden;
    private final TrapTypeEnumPlayground trapType;
    private int x; 
    private int y;
    public BearTrapPlayground(boolean hidden) {
        this.hidden = hidden;
        this.trapType = TrapTypeEnumPlayground.BEAR_TRAP;
    }

    @Override
    public void applyEffect(Player player) {
        Random rand = new Random();
        int immobilizedTurns = rand.nextInt(4) + 1; // 1d4 turns
        player.setImmobile(immobilizedTurns);
    }

    @Override
    public String trigger(Player player) {
        
        applyEffect(player); 
        this.hidden = false; 
        return trapType.getMessage() + " You are stuck for " + player.getImmobile() + " turns!";
    }

    @Override
    public boolean isHidden() {
        // TODO Auto-generated method stub
        return hidden;
    }

    @Override
    public void reveal() {
        this.hidden = false;
    }

    public TrapTypeEnumPlayground getTrapType() {
        return trapType;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
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
