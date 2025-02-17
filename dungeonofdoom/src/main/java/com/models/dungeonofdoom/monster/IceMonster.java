package com.models.dungeonofdoom.monster;

import java.util.Random;

import com.models.dungeonofdoom.enums.MonsterEnum;

public class IceMonster extends Monster{

    public IceMonster(Random rand) {
        super(MonsterEnum.ICEMONSTER, rand);
    }

    @Override
    public void specialAbility() {
        System.out.println("The Ice Monster!");
    }
}