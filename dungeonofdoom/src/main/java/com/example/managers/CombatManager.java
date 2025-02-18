package com.example.managers;

import com.models.Player;
import com.models.dungeonofdoom.monster.Monster;

public class CombatManager {
     public static void handleCombat(Player player, Monster monster) {
        System.out.println("Combat initiated between " + player.getName() + " and " + monster.getName());

        // Player attacks first
        int playerDamage = player.attack(monster);
        monster.takeDmg(playerDamage);
        System.out.println(player.getName() + " attacks " + monster.getName() + " for " + playerDamage + " damage!");

        if (monster.isDead()) {
            System.out.println(monster.getName() + " has been defeated!");
            return; // No counterattack if the monster is dead
        }

        // Monster attacks back
        int monsterDamage = monster.calculateDmg();
        player.takeDamage(monsterDamage);
        System.out.println(monster.getName() + " attacks " + player.getName() + " for " + monsterDamage + " damage!");

        if (player.isDead()) {
            System.out.println(player.getName() + " has died. Game Over!");
            // Handle player death (perhaps end the game)
        }
    }
}
