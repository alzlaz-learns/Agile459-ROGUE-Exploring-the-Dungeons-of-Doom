package com.models.dungeonofdoom.enums;

import java.util.Random;

import com.models.Player;
import com.models.dungeonofdoom.Items.Item;
import com.models.dungeonofdoom.Items.Potion.PotionEffect;
import com.models.dungeonofdoom.dungeonfloor.DungeonFloor;
import com.models.dungeonofdoom.monster.Monster;

public class MonsterDetection implements PotionEffect{
    private final Random random;

    public MonsterDetection(){
        this.random = new Random();
    }

    public MonsterDetection(Random random){
        this.random = random;
    }
    @Override
    public void applyToPlayer(Player player) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'applyToPlayer'");
    }

    @Override
    public void applyToMonster(Monster monster) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'applyToMonster'");
    }

    @Override
    public String messageStringPlayer(Player player) {
        return "You sense the presence of monsters!";
    }

    @Override
    public String messageStringMonster(Monster monster) {
        return ""; 
    }

    @Override
    public void applyToPlayer(Player player, DungeonFloor dungeonFloor) {
        int duration = random.nextInt(8) + 1 + 20; // 1d8 + 20 turns
        
        if(!dungeonFloor.getMonsters().isEmpty()){
            for (Monster m : dungeonFloor.getMonsters()) {
                m.reveal(duration);  
            }
        }
    }

}
