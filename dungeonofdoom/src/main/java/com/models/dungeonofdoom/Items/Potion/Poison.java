package com.models.dungeonofdoom.Items.Potion;

import java.util.Random;

import com.models.Player;
import com.models.dungeonofdoom.Items.ItemEffect;
import com.models.dungeonofdoom.dungeonfloor.DungeonFloor;
import com.models.dungeonofdoom.monster.Monster;

public class Poison implements ItemEffect{

    @Override
    public void applyToPlayer(Player player, DungeonFloor d) {
        if(player.hasRingOfSustain()){
            System.out.println("has ring of sustain");
            return;
        }
        Random r = new Random();
        int hit = r.nextInt(3) + 1;
        player.adjustStrength(-hit);
    }

    @Override
    public void applyToMonster(Monster monster) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public String messageStringPlayer(Player player) {
        
        String res;
        if(player.hasRingOfSustain()){
            res = "You feel momentarily sick";
            
        }else{
            res = "You feel very sick";
            System.out.println(player.getStrength());
        }
        return res;
        
    }

    @Override
    public String messageStringMonster(Monster monster) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'messageStringMonster'");
    }

}
