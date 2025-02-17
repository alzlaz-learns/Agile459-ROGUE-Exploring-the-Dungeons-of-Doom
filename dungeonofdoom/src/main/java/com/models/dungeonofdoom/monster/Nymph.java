package com.models.dungeonofdoom.monster;

import java.util.Random;

import com.models.dungeonofdoom.enums.MonsterEnum;

public class Nymph extends Monster {

    public Nymph(Random rand) {
        super(MonsterEnum.NYMPH, rand);
        //TODO Auto-generated constructor stub
    }

    @Override
    public void specialAbility() {
        // TODO Auto-generated method stub
        System.out.println("The nymph");
    }
    
}
