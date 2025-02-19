package com.example.managers;

import com.models.Player;
import com.models.dungeonofdoom.dungeonfloor.DungeonFloor;
import com.models.dungeonofdoom.monster.Monster;

public class CombatManager {

    /*
     * Attacks are not guaranteed to hit and each attack may deal a variable amount of damage. To
        hit, an attack roll must be greater than the defender’s armor class. For each attack, roll 1d20,
        then compare that to the following: 20 - attacker’s level - defender’s armor class.
        For example, if a level 5 Wraith attacks a player with an armor class of 3, then the Wraith will
        need at least (20 - 5) - 3 = 12 to hit.

     */


     //need to make a method for calculating the odds of an attack hitting
    
     public static void monsterAttack(Player player, Monster monster) {
        //eventually need to pass things out maybe to make messages.
        System.out.println(monster.getName() + ": is attacking " + player.getName());

        int damage = monster.calculateDmg();

        player.takeDamage(damage);

        monster.specialAbility(player);
    }

    public static void playerAttack(Player player, Monster monster, DungeonFloor dungeonFloor){
        System.out.println( player.getName()+ ": is attacking " + monster.getName());

        int damage = player.calculateDmg();

        System.out.println(monster.getName() + "is taking " + damage);
        monster.takeDmg(damage);

        if(monster.isDead()){
            dungeonFloor.removeMonster(monster);
            System.out.println(monster.getName()+ " died");
        }
    }
}
