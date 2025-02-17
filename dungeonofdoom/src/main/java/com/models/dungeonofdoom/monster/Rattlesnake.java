package com.models.dungeonofdoom.monster;

import java.util.Random;

import com.models.dungeonofdoom.enums.MonsterEnum;

public class Rattlesnake extends Monster{

    public Rattlesnake(Random rand) {
        super(MonsterEnum.RATTLESNAKE, rand);
        //TODO Auto-generated constructor stub
    }

    @Override
    public void specialAbility() {
        // TODO Auto-generated method stub
        System.out.println("The rattle snake");    
    }

}
