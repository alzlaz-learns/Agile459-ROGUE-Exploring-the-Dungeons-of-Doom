package com.models.dungeonofdoom.monster;

import java.util.Random;

import com.models.Player;
import com.models.dungeonofdoom.enums.MonsterEnum;

public class Wraith extends Monster {

    public Wraith(Random rand) {
        super(MonsterEnum.WRAITH, rand);
        //TODO Auto-generated constructor stub
    }

   

    @Override
    public void specialAbility(Player player) {
       int modifier = rand.nextInt(3) + 1;
       player.adjustExperience(-modifier);
    }
    
}
