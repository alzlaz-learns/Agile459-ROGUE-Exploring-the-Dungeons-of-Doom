package com.models.dungeonofdoom.Items.Scroll;

import java.util.Random;

import com.models.Player;
import com.models.dungeonofdoom.Items.ItemEffect;
import com.models.dungeonofdoom.dungeonfloor.DungeonFloor;
import com.models.dungeonofdoom.monster.Monster;

public class ConfusionScroll implements ItemEffect{

    private final Random random;
    
    public ConfusionScroll(Random random) {
        this.random = random;
    }

    public ConfusionScroll() {
        this.random = new Random();
    }


    @Override
    public void applyToMonster(Monster monster) {
       
    }

    @Override
    public String messageStringPlayer(Player player) {
        return "Your hands begin to glow red!";
    }

    @Override
    public String messageStringMonster(Monster monster) {
        return "The monster looks confused!";
    }

    @Override
    public void applyToPlayer(Player player, DungeonFloor floor) {
        int duration = random.nextInt(8) + 1 + 20; 

        int playerX = player.getX();
        int playerY = player.getY();

        for (Monster monster : floor.getMonsters()) {
            int monsterX = monster.getX();
            int monsterY = monster.getY();

           
            int distance = Math.abs(playerX - monsterX) + Math.abs(playerY - monsterY);

            if (distance <= 2) { 
                monster.applyConfused(duration);
            }
        }
    }

}
