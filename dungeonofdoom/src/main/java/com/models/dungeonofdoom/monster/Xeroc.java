package com.models.dungeonofdoom.monster;

import java.util.Random;

import com.models.dungeonofdoom.enums.MonsterEnum;

public class Xeroc extends Monster {

    public Xeroc(Random rand) {
        super(MonsterEnum.XEROC, rand);
        //TODO Auto-generated constructor stub
    }

    @Override
    public void specialAbility() {
        // TODO Auto-generated method stub
        System.out.println("the xeroc");
    }
    
}
