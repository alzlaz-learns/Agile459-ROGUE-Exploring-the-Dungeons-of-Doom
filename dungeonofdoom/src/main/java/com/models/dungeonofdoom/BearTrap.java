package com.models.dungeonofdoom;

import java.util.Random;

import com.models.Player;
import com.models.dungeonofdoom.enums.TrapTypeEnum;


public class BearTrap extends AbstractTrap{
    //dependency injection implemented for testing but result created better class layout
    Random rand;

    public BearTrap(boolean hidden, Random rand) {
        super(hidden, TrapTypeEnum.BEAR_TRAP);
        this.rand = rand;
    }

    public void applyEffect(Player player) {
        int immobilizedTurns = rand.nextInt(4) + 1; // 1d4 turns
        player.setImmobile(immobilizedTurns);
    }

    @Override
    public String trigger(Player player) {
        this.reveal();
        applyEffect(player);
        return trapType.getMessage(); 
    }
}
