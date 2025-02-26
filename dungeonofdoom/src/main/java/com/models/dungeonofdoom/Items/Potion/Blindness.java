package com.models.dungeonofdoom.Items.Potion;

import java.util.Random;

import com.models.Player;
import com.models.dungeonofdoom.monster.Monster;

public class Blindness implements PotionEffect{
    private final Random random;

    
    public Blindness(Random random) {
        this.random = random;
    }

    @Override
    public void applyToPlayer(Player player) {
        int duration = randomDuration();
        player.applyBlind(duration);
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
        boolean randomBoolean = random.nextBoolean();
        return randomBoolean ? 20 + (int)(20 * .1): 20 - (int)(20 * .1);
    }

}
