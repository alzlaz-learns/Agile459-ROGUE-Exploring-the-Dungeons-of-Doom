package com.models.dungeonofdoom.monster;

import java.util.Random;

import com.models.Player;
import com.models.dungeonofdoom.enums.MonsterEnum;

public class Leprechaun extends Monster{

    public Leprechaun(Random rand) {
        super(MonsterEnum.LEPRECHAUN, rand);
    }

    @Override
    public void specialAbility(Player player) {
        // there is nothing specifiying how much gold is stolen
        int goldStolen = rand.nextInt(4) + 1;
        System.out.println("amount stolen: " + goldStolen);
        player.adjustGold(-goldStolen);
    }
}
