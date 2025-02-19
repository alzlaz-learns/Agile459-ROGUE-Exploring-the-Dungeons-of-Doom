package com.models.dungeonofdoom.monster;

import java.util.Random;

import com.models.Player;
import com.models.dungeonofdoom.enums.MonsterEnum;

public class Nymph extends Monster {

    public Nymph(Random rand) {
        super(MonsterEnum.NYMPH, rand);
        //TODO Auto-generated constructor stub
    }

    @Override
    public void specialAbility(Player player) {
        //to be be implemented when player has items!
        System.out.println("Nymph has stolen an item!");
    }

    
}
