package com.models.dungeonofdoom.Items.Potion;

import java.util.Random;

import com.models.Player;
import com.models.dungeonofdoom.monster.Monster;

public class Blindness implements PotionEffect{
    
    @Override
    public void applyToPlayer(Player player) {
        int duration = randomDuration();
        // need to eventually apply blind status effect to player
    }

    @Override
    public void applyToMonster(Monster monster) {
        int duration = randomDuration();
        // need to eventually apply blind status effect to player
    }

    @Override
    public String messageStringPlayer(Player player) {
       return "A cloak of darkness falls around you";
    }

    @Override
    public String messageStringMonster(Monster monster) {
        return ("The " + monster.getName() + " appears confused");
    }

    private int randomDuration(){
        boolean randomBoolean = Math.random() < 0.5;
        return randomBoolean ? (int)(20 * .01): (int)(20 * -.01);
    }

}
