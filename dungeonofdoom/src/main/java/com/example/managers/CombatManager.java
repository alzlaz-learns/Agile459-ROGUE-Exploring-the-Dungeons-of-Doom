package com.example.managers;

import java.util.Random;

import com.models.Player;
import com.models.dungeonofdoom.dungeonfloor.DungeonFloor;
import com.models.dungeonofdoom.monster.Monster;

public class CombatManager {

    private static final Random rand = new Random();

    private static int getAttackModifier(int strength) {
        if (strength < 8) return -7;
        if (strength < 17) return -6;
        if (strength < 19) return -4;
        if (strength < 21) return -3;
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
        hit, an attack roll must be greater than the defender’s armor class. For each attack, roll 1d20,
        then compare that to the following: 20 - attacker’s level - defender’s armor class.
        For example, if a level 5 Wraith attacks a player with an armor class of 3, then the Wraith will
        need at least (20 - 5) - 3 = 12 to hit.

     */

    private static boolean chanceToHit(int attackerLevel, int defenderArmor, int strength) {
        int roll = rand.nextInt(20) + 1; 
        int requiredRoll = (20 - attackerLevel - defenderArmor) + getAttackModifier(strength);; 

        System.out.println("Attack Roll: " + roll + " | Needed: " + requiredRoll);

        return roll >= requiredRoll;
    }

     //need to make a method for calculating the odds of an attack hitting
     public static void monsterAttack(Player player, Monster monster) {
        //eventually need to pass things out maybe to make messages.
        System.out.println(monster.getName() + ": is attacking " + player.getName());

        if (chanceToHit(monster.getLevel(), player.getArmor(), 10)) {
            int damage = monster.calculateDmg();
            player.takeDamage(damage);
            monster.specialAbility(player);
        } else {
            System.out.println(monster.getName() + " missed"); //to be changed eventually to put messages into a queue
        }
    }

    public static void playerAttack(Player player, Monster monster, DungeonFloor dungeonFloor){
        System.out.println( player.getName()+ ": is attacking " + monster.getName());

        if (chanceToHit(player.getLevel(), monster.getAmr(), player.getStrength())) {
            int baseDamage = player.calculateDmg();
            int totalDamage = Math.max(1, baseDamage + getDamageModifier(player.getStrength()));

            monster.takeDmg(totalDamage);
            System.out.println(player.getName() + " hits " + monster.getName() + " for " + totalDamage + " damage!");

            if (monster.isDead()) {
                System.out.println(monster.getName() + " has been slain!");
                dungeonFloor.removeMonster(monster);
            }
        } else {
            System.out.println(player.getName() + " missed"); //to be changed eventually to put messages into a queue
        }
    }

    public static void combatOrdering(Player player, Monster monster, DungeonFloor dungeonFloor){

        // mean monsters attack first
        if (monster.isMean()) {
            System.out.println(monster.getName() + " is Mean and attacks first!");
            monsterAttack(player, monster);

            // Check if player is still alive before they can retaliate
            if (!player.isDead()) {
                playerAttack(player, monster, dungeonFloor);
            }
        } else {
            // non Mean monsters attack second
            playerAttack(player, monster, dungeonFloor);

            
            if (!monster.isDead()) {
                monsterAttack(player, monster);
            }
        }
    }
}
