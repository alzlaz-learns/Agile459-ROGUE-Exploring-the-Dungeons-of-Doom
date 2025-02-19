package com.models.dungeonofdoom.monster;

import java.util.Random;

import com.models.Player;
import com.models.dungeonofdoom.enums.MonsterEnum;

public class Vampire extends Monster {

    public Vampire(Random rand) {
        super(MonsterEnum.VAMPIRE, rand);
        //TODO Auto-generated constructor stub
    }

    @Override
    public void specialAbility(Player player) {
       int modifications = -1;
       player.adjustMaxHealth(modifications);
    }

  
}
