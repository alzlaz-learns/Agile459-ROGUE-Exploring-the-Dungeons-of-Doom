package com.models.dungeonofdoom.monster;

import java.util.Random;

import com.models.Player;
import com.models.dungeonofdoom.enums.MonsterEnum;

public class Rattlesnake extends Monster{

    public Rattlesnake(Random rand) {
        super(MonsterEnum.RATTLESNAKE, rand);
        //TODO Auto-generated constructor stub
    }

   

    @Override
    public void specialAbility(Player player) {
      int reduceStrength = rand.nextInt(3) + 1;
      System.out.println("players strength is reduced by: " + reduceStrength);
      player.adjustStrength(-reduceStrength);
    }

}
