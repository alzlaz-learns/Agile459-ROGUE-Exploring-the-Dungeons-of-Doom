package com.models.dungeonofdoom.monster;

import java.util.Random;

import com.models.Player;
import com.models.dungeonofdoom.enums.MonsterEnum;

public class Aquator extends Monster{

    public Aquator(Random rand) {
        super(MonsterEnum.AQUATOR, rand);
    }

    

    @Override
    public void specialAbility(Player player) {
        int modification = -1;
        player.adjustArmor(modification);
    }

}
