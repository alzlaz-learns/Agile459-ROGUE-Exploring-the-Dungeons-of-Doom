package com.example.managers;

import java.util.Random;

import com.models.Player;
import com.models.dungeonofdoom.dungeonfloor.DungeonFloor;
import com.models.dungeonofdoom.monster.Monster;
import com.example.ui.JFrameUI;
import com.models.dungeonofdoom.Items.Stick.Lightning;
import com.models.dungeonofdoom.Items.Stick.Stick;
import com.models.dungeonofdoom.enums.StickEnum;
import com.models.dungeonofdoom.Items.Item;

public class CombatManager {

    private static final Random rand = new Random();
    private static int globalTurnCounter = 0;

    private static int getAttackModifier(int strength) {
        if (strength < 8) return -7;
        if (strength < 17) return -4;
        if (strength < 19) return -3;
        if (strength < 21) return -2;
        if (strength < 31) return -1;
        return 0;
    }

    private static int getDamageModifier(int strength) {
        if (strength < 8) return -7;
        if (strength < 16) return -6;
        if (strength < 17) return -5;
        if (strength < 18) return -4;
        if (strength < 20) return -3;
        if (strength < 22) return -2;
        if (strength < 31) return -1;
        return 0;
    }

    /*
     * Attacks are not guaranteed to hit and each attack may deal a variable amount of damage. To
        hit, an attack roll must be greater than the defender's armor class. For each attack, roll 1d20,
        then compare that to the following: 20 - attacker's level - defender's armor class.
        For example, if a level 5 Wraith attacks a player with an armor class of 3, then the Wraith will
        need at least (20 - 5) - 3 = 12 to hit.

     */

    private static boolean chanceToHit(int attackerLevel, int defenderArmor, int strength) {
        int roll = rand.nextInt(20) + 1; // 1d20 roll
        int requiredRoll = 20 - attackerLevel - defenderArmor;
        int attackModifier = getAttackModifier(strength);
        
        // Subtract the attack modifier since negative modifiers make it harder to hit
        roll -= attackModifier;

        return roll >= requiredRoll;
    }

     //need to make a method for calculating the odds of an attack hitting
     public static void monsterAttack(Player player, Monster monster, JFrameUI frame) {
        frame.updateMessage(monster.getName() + " attacks " + player.getName() + "!");
        frame.updateGameScreen();

        // All monsters have base strength of 10 per requirements
        if (chanceToHit(monster.getLevel(), player.getArmor(), 10)) {
            int baseDamage = monster.calculateDmg();
            int strengthBonus = getDamageModifier(10); // Base monster strength of 10
            int totalDamage = Math.max(1, baseDamage + strengthBonus);
            
            player.takeDamage(totalDamage);
            frame.updateMessage(monster.getName() + " hits for " + totalDamage + " damage!");
            frame.updateGameScreen();
            monster.specialAbility(player);
        } else {
            frame.updateMessage(monster.getName() + "'s attack misses!");
            frame.updateGameScreen();
        }
    }

    public static void playerAttack(Player player, Monster monster, DungeonFloor dungeonFloor, JFrameUI frame) {
        frame.updateMessage("You attack the " + monster.getName() + "!");
        frame.updateGameScreen();

        if(monster.getHoldStatus()){
            monster.removeHold();
        }

        boolean hasLightningStaff = false;
        Lightning lightningEffect = null;
        
        // Check equipment
        for (Item item : player.getEquippedItems()) {
            // Check if the item is a Stick with LIGHTNING type
            if (item instanceof Stick && ((Stick) item).getStickType() == StickEnum.LIGHTNING) {
                hasLightningStaff = true;
                break;
            }
        }

        if (chanceToHit(player.getLevel(), monster.getAmr(), player.getStrength())) {
            int baseDamage = player.calculateDmg();
            int strengthBonus = getDamageModifier(player.getStrength());
            // Player's equipment bonuses are handled in calculateDmg()
            int totalDamage = Math.max(1, baseDamage + strengthBonus);

            monster.takeDmg(totalDamage);
            frame.updateMessage("You hit the " + monster.getName() + " for " + totalDamage + " damage!");
            frame.updateGameScreen();

            if (monster.isDead()) {
                frame.updateMessage("The " + monster.getName() + " has been slain!");
                int expRecieved = monster.getExp();
                player.adjustExperience(expRecieved);
                frame.updateGameScreen();
                dungeonFloor.removeMonster(monster);
            }
        } else {
            frame.updateMessage("Your attack misses!");
            
            // If player has lightning staff and misses, start the bouncing effect
            if (hasLightningStaff) {
                lightningEffect = new Lightning(rand, dungeonFloor);
                TurnManager.addLightningEffect(lightningEffect); //maybe hacky impl, forgive me 
                frame.updateMessage(lightningEffect.messageStringPlayer(player));
            }
            
            frame.updateGameScreen();
        }
    }

    public static void combatOrdering(Player player, Monster monster, DungeonFloor dungeonFloor, JFrameUI frame) {
        // Increment turn counter at the start of each combat round
        incrementTurnCounter();

        // mean monsters attack first
        if (monster.isMean()) {
            frame.updateMessage("The " + monster.getName() + " is Mean and attacks first!");
            frame.updateGameScreen();
            monsterAttack(player, monster, frame);

            // Check if player is still alive before they can retaliate
            if (!player.isDead()) {
                playerAttack(player, monster, dungeonFloor, frame);
            }
        } else {
            // non Mean monsters attack second
            playerAttack(player, monster, dungeonFloor, frame);
            
            if (!monster.isDead()) {
                monsterAttack(player, monster, frame);
            }
        }
    }

    /**
     * Increments the global turn counter
     */
    public static void incrementTurnCounter() {
        globalTurnCounter++;
    }
    
    /**
     * Gets the current global turn count
     * @return the current global turn count
     */
    public static int getTurnCounter() {
        return globalTurnCounter;
    }
}
