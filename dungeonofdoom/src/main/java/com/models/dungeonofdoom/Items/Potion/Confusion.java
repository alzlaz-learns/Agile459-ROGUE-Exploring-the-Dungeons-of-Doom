package com.models.dungeonofdoom.Items.Potion;

import java.util.Random;

import com.models.Player;
import com.models.dungeonofdoom.monster.Monster;

public class Confusion implements PotionEffect{

    private final Random random;
    
    public Confusion(Random random) {
        this.random = random;
    }

    public Confusion() {
        this.random = new Random();
    }
    @Override
    public void applyToPlayer(Player player) {
        int duration = randomDuration();
        player.setConfused(duration);
    }

    @Override
    public void applyToMonster(Monster monster) {
       int duration = randomDuration();
        // need to eventually apply blind status effect to player
    }

    @Override
    public String messageStringPlayer(Player player) {
        //this might need to be changed in the future
        return "Wait, what's going on? Huh? What? Who? You feel less confused now";
    }

    @Override
    public String messageStringMonster(Monster monster) {
        //this might need to be changed in the future
        return "The " + monster.getName() + " appears confused The " + monster.getName() + " seems less confused now.";
        

    }

    private int randomDuration(){
        return random.nextInt(8) + 20 + 1;
    }
    
}
