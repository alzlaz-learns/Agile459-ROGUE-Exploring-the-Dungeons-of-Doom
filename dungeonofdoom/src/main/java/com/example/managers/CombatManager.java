package com.example.managers;

import com.models.Player;
import com.models.dungeonofdoom.dungeonfloor.DungeonFloor;
import com.models.dungeonofdoom.monster.Monster;

public class CombatManager {

    
     public static void monsterAttack(Player player, Monster monster) {
        System.out.println(monster.getName() + ": is attacking " + player.getName());

        int damage = monster.calculateDmg();

        System.out.println("Player is taking " + damage);
        player.takeDamage(damage);

        
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
