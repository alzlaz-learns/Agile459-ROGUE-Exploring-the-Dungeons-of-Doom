package com.models.dungeonofdoom.monster;

import java.util.Random;

import com.models.Player;
import com.models.dungeonofdoom.enums.MonsterEnum;

public class Xeroc extends Monster {

    public Xeroc(Random rand) {
        super(MonsterEnum.XEROC, rand);
        //TODO Auto-generated constructor stub
    }

    @Override
    public void specialAbility(Player player) {
      // special ability isnt a thing i think
      // this is more going to be connected to if its xeroc when inactive
      //its going to look like an item. 
    }
}
