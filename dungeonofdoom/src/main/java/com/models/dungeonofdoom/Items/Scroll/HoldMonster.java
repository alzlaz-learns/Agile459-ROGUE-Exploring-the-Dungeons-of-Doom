package com.models.dungeonofdoom.Items.Scroll;

import com.models.Player;
import com.models.dungeonofdoom.Items.ItemEffect;
import com.models.dungeonofdoom.dungeonfloor.DungeonFloor;
import com.models.dungeonofdoom.monster.Monster;

public class HoldMonster implements ItemEffect{

    @Override
    public void applyToPlayer(Player player) {
       
    }

    @Override
    public void applyToMonster(Monster monster) {
        
    }

    @Override
    public String messageStringPlayer(Player player) {
       return "";
    }

    @Override
    public String messageStringMonster(Monster monster) {
        return "";
    }

    public void applyToPlayer(Player player, DungeonFloor floor) {
        
        int playerX = player.getX();
        int playerY = player.getY();

        for (Monster monster : floor.getMonsters()) {
            int monsterX = monster.getX();
            int monsterY = monster.getY();

           
            int distance = Math.abs(playerX - monsterX) + Math.abs(playerY - monsterY);

            if (distance <= 2) { 
                monster.applyHold();
            }
        }
    }
}
