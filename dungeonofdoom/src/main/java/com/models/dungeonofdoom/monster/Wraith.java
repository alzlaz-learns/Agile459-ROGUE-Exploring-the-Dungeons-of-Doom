package com.models.dungeonofdoom.monster;

import java.util.Random;

import com.models.dungeonofdoom.enums.MonsterEnum;

public class Wraith extends Monster {

    public Wraith(Random rand) {
        super(MonsterEnum.WRAITH, rand);
        //TODO Auto-generated constructor stub
    }

    @Override
    public void specialAbility() {
        // TODO Auto-generated method stub
        System.out.println("the wraith");
    }
    
}
