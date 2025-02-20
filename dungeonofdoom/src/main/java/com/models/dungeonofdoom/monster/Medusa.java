package com.models.dungeonofdoom.monster;

import java.util.Random;

import com.models.Player;
import com.models.dungeonofdoom.enums.MonsterEnum;

public class Medusa extends Monster{

    public Medusa(Random rand){
        super(MonsterEnum.MEDUSA, rand);
    }

    @Override
    public void specialAbility(Player player) {
       int confusion = rand.nextInt(4) + 1; // Freeze for 1d4 turns
       System.out.println("player is confused for: " + confusion);
       player.setConfused(confusion);
    }
    
    
}
