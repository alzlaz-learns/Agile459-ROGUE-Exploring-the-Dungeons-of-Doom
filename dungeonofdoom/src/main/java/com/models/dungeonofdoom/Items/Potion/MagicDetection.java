package com.models.dungeonofdoom.Items.Potion;

import java.util.Random;

import com.models.Player;
import com.models.dungeonofdoom.Items.Item;
import com.models.dungeonofdoom.dungeonfloor.DungeonFloor;
import com.models.dungeonofdoom.monster.Monster;

public class MagicDetection implements PotionEffect{
    private final Random random;

    public MagicDetection(){
        this.random = new Random();
    }

    public MagicDetection(Random random){
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
        return "You sense the presence of magic!";
    }

    @Override
    public String messageStringMonster(Monster monster) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'messageStringMonster'");
    }

    @Override
    public void applyToPlayer(Player player, DungeonFloor dungeonFloor){
        for(Item i: dungeonFloor.getItems()){
            dungeonFloor.revealItemLocation(i);
        }
    }
}
