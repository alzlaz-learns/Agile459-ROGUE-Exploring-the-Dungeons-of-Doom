package com.models.dungeonofdoom.Items.Stick;

import java.util.Random;

import com.models.Player;
import com.models.dungeonofdoom.Items.ItemEffect;
import com.models.dungeonofdoom.monster.Monster;
import com.models.dungeonofdoom.dungeonfloor.DungeonFloor;
import com.models.dungeonofdoom.dungeonfloor.Room;
import com.models.dungeonofdoom.Helper.Pair;

public class Lightning implements ItemEffect {

    private Random random;
    private Pair<Integer, Integer> bounceDuration = new Pair<>(1,4);
    private int damage = 8; // 1d8 damage
    private boolean isBouncing = false;
    private int remainingBounces = 0;
    private DungeonFloor dungeonFloor;
    
    public Lightning(Random random, DungeonFloor dungeonFloor) {
        this.random = random;
        this.dungeonFloor = dungeonFloor;
    }
    
    /**
     * Calculates lightning damage (1d8)
     * @return damage amount
     */
    public int calculateDamage() {
        return random.nextInt(damage) + 1; // 1d8 damage
    }
    
    /**
     * Starts the bouncing effect after a miss
     */
    public void startBouncing() {
        isBouncing = true;
        remainingBounces = random.nextInt(bounceDuration.getB()) + bounceDuration.getA();
    }
    
    /**
     * Checks if lightning is currently bouncing
     * @return true if lightning is bouncing
     */
    public boolean isBouncing() {
        return isBouncing && !hasExpired();
    }
    
    /**
     * Checks if the lightning effect has expired
     * @return true if the effect has expired
     */
    public boolean hasExpired() {
        return remainingBounces == 0;
    }
    
    /**
     * Stops the bouncing effect (when lightning hits something)
     */
    public void stopBouncing() {
        isBouncing = false;
        remainingBounces = 0;
    }
    
    /**
     * Processes the lightning effect for the current turn
     * @param player The player
     * @param monster The monster
     * @return A message describing what happened, or null if nothing happened
     */
    public String processTurn(Player player) {
        if (!isBouncing() || hasExpired()) {
            return null;
        }

        
        // Roll once per turn to determine what happens
        int playerRoll = random.nextInt(10);
        
        // 10% chance to hit player (roll == 0)
        if (playerRoll == 0) {
            int dmg = calculateDamage();
            player.takeDamage(dmg);
            stopBouncing();
            return "The bouncing lightning strikes you for " + dmg + " damage!";
        }
        

        Room playerRoom = dungeonFloor.getRoomAt(player.getX(), player.getY());
        
        if (playerRoom != null) {
            // Apply drain effect to all monsters in the same room
            for (Monster monster : dungeonFloor.getMonsters()) {
                int dmg = calculateDamage();
                if (playerRoom.contains(monster.getX(), monster.getY())) {
                    int monsterRoll = random.nextInt(10);
                    if (monsterRoll == 0) { // 10% chance to hit monster
                        
                        monster.takeDmg(dmg);
                        stopBouncing();
                        return "The bouncing lightning strikes " + monster.getName() + " for " + dmg + " damage!";
                    }
                }
                if (monster.isDead()) {
                    player.adjustExperience(monster.getExp());
                    return "The bouncing lightning strikes " + monster.getName() + 
                           " for " + dmg + " damage! The " + monster.getName() + 
                           " has been slain by the bouncing lightning!";
                } else {
                    return "The bouncing lightning strikes " + monster.getName() + 
                           " for " + dmg + " damage!";
                }
            }
        }
            
        
        // 80% chance nothing happens (just continues bouncing)
        remainingBounces--;
        return "The lightning bolt continues to bounce around the room.";
    }

    @Override
    public void applyToPlayer(Player player, DungeonFloor df) {
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
