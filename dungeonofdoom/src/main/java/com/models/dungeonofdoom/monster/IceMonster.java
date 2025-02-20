package com.models.dungeonofdoom.monster;

import java.util.Random;

import com.models.Player;
import com.models.dungeonofdoom.enums.MonsterEnum;

public class IceMonster extends Monster{

    public IceMonster(Random rand) {
        super(MonsterEnum.ICEMONSTER, rand);
    }

    @Override
    public void specialAbility(Player player) {
        int freezeTurns = rand.nextInt(4) + 1; // Freeze for 1d4 turns
        player.setImmobile(freezeTurns);
        System.out.println("The Ice Monster freezes you for " + freezeTurns + " turns!");
    }

   
}