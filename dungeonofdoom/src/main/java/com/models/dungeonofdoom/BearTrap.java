package com.models.dungeonofdoom;

import java.util.Random;

import com.models.Player;
import com.models.dungeonofdoom.enums.TrapTypeEnum;


public class BearTrap extends AbstractTrap{

    public BearTrap(boolean hidden) {
        super(hidden, TrapTypeEnum.BEAR_TRAP);
    }

    @Override
    public void applyEffect(Player player) {
        Random rand = new Random();
        int immobilizedTurns = rand.nextInt(4) + 1; // 1d4 turns
        player.setImmobile(immobilizedTurns);
    }
}
