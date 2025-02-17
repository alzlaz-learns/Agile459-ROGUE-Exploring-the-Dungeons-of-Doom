package com.models.dungeonofdoom.monster;

import java.util.Random;

import com.models.dungeonofdoom.enums.MonsterEnum;

public class GeneralMonster extends Monster{
    public GeneralMonster(MonsterEnum type, Random rand) {
        super(type, rand);
    }

    @Override
    public void specialAbility() {
        // No special ability
    }
}
