package com.models.dungeonofdoom.monster;

import java.util.Random;

import com.models.dungeonofdoom.enums.MonsterEnum;

public class Leprechaun extends Monster{

    public Leprechaun(Random rand) {
        super(MonsterEnum.LEPRECHAUN, rand);
    }

    @Override
    public void specialAbility() {
        System.out.println("The Leprechaun!");
    }

}
