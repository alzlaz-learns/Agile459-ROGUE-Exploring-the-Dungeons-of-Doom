package com.models.dungeonofdoom.Items.Potion;

import java.util.Random;

import com.models.Player;
import com.models.dungeonofdoom.Items.Item;
import com.models.dungeonofdoom.Items.ItemEffect;
import com.models.dungeonofdoom.dungeonfloor.DungeonFloor;
import com.models.dungeonofdoom.monster.Monster;

public class MonsterDetection implements ItemEffect{
    private final Random random;

    public MonsterDetection(){
        this.random = new Random();
    }

    public MonsterDetection(Random random){
        this.random = random;
    }
    @Override
    public void applyToPlayer(Player player, DungeonFloor d) {
        // TODO Auto-generated method stub
        int duration = random.nextInt(8) + 1 + 20; // 1d8 + 20 turns
        player.reveal(duration);
    }

    @Override
    public void applyToMonster(Monster monster) {
    }

    @Override
    public String messageStringPlayer(Player player) {
        return "You sense the presence of monsters!";
    }

    @Override
    public String messageStringMonster(Monster monster) {
        return ""; 
    }
}
