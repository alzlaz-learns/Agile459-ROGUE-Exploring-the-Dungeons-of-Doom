package com.models.dungeonofdoom.monster;

import java.util.Random;

import com.models.dungeonofdoom.enums.MonsterEnum;

public class Medusa extends Monster{

    public Medusa(Random rand){
        super(MonsterEnum.MEDUSA, rand);
    }
    @Override
    public void specialAbility() {
        // TODO Auto-generated method stub
        System.out.println("The Medusa!");   
    }
    
}
