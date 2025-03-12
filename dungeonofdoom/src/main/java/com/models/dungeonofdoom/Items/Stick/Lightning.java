package com.models.dungeonofdoom.Items.Stick;

import java.util.Random;

import com.models.Player;
import com.models.dungeonofdoom.Items.ItemEffect;
import com.models.dungeonofdoom.monster.Monster;

import com.models.dungeonofdoom.Helper.Pair;

public class Lightning implements ItemEffect {

    private Random random;
    private Pair<Integer, Integer> bounceDuration= new Pair<>(1,4);
    private int damage = 8; // 1d8 damage
    private boolean isBouncing = false;
    private int remainingBounces = 0;

    public Lightning(Random random) {
        this.random = random;
    }
    
    /**
     * Calculates lightning damage (1d8)
     * @return damage amount
     */
    public int calculateDamage() {
        return random.nextInt(damage) + 1; // 1d8 damage
    }
    
    /**
     * Determines if the bouncing lightning hits something
     * @return true if lightning hits during bounce (10% chance)
     */
    public boolean hitsDuringBounce() {
        return random.nextInt(10) == 0; // 10% chance to hit while bouncing
    }
    
    /**
     * Starts the bouncing effect after a miss
     */
    public void startBouncing() {
        isBouncing = true;
        remainingBounces = random.nextInt(bounceDuration.getSecond()) + bounceDuration.getFirst();
    }
    
    /**
     * Checks if lightning is currently bouncing
     * @return true if lightning is bouncing
     */
    public boolean isBouncing() {
        return isBouncing && remainingBounces > 0;
    }
    
    /**
     * Decrements the bounce counter
     * @return true if lightning is still bouncing
     */
    public boolean continueBouncing() {
        if (isBouncing) {
            remainingBounces--;
            if (remainingBounces <= 0) {
                isBouncing = false;
                return false;
            }
            return true;
        }
        return false;
    }
    
    /**
     * Stops the bouncing effect (when lightning hits something)
     */
    public void stopBouncing() {
        isBouncing = false;
        remainingBounces = 0;
    }

    @Override
    public void applyToPlayer(Player player) {
        // This is called when lightning bounces back and hits the player
        int dmg = calculateDamage();
        player.takeDamage(dmg);
        stopBouncing(); // Lightning stops bouncing after hitting
    }

    @Override
    public void applyToMonster(Monster monster) {
        // The actual hit calculation should be done by CombatManager
        // This method would be called after CombatManager determines a hit
        int dmg = calculateDamage();
        monster.takeDmg(dmg);
        stopBouncing(); // Lightning stops bouncing after hitting
    }

    @Override
    public String messageStringPlayer(Player player) {
        if (isBouncing) {
            return "The lightning bolt bounces around the room!";
        }
        return "You shoot a lightning bolt!";
    }

    @Override
    public String messageStringMonster(Monster monster) {
        return "A lightning bolt strikes " + monster.getName() + "!";
    }
}
