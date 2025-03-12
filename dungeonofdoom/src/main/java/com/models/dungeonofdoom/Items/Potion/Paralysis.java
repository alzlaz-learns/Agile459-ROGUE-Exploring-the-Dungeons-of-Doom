package com.models.dungeonofdoom.Items.Potion;

import java.util.Random;

import com.models.Player;
import com.models.dungeonofdoom.Items.ItemEffect;
import com.models.dungeonofdoom.monster.Monster;

public class Paralysis implements ItemEffect{

    private final Random random;
    
    public Paralysis(Random random) {
        this.random = random;
    }

    public Paralysis() {
        this.random = new Random();
    }
    @Override
    public void applyToPlayer(Player player) {
        int duration = random.nextInt(4) + 1;
        player.setImmobile(duration);
    }

    @Override
    public void applyToMonster(Monster monster) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'applyToMonster'");
    }

    @Override
    public String messageStringPlayer(Player player) {
        return "You can’t move";    
    }

    @Override
    public String messageStringMonster(Monster monster) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'messageStringMonster'");
    }

}
