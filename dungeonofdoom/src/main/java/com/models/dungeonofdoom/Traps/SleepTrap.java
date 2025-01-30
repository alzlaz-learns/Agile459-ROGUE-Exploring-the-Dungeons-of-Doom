package com.models.dungeonofdoom.Traps;

import java.util.Random;

import com.models.Player;
import com.models.dungeonofdoom.enums.TrapTypeEnum;

public class SleepTrap extends AbstractTrap{

    Random rand;

    public SleepTrap(boolean hidden, Random rand) {
        super(hidden, TrapTypeEnum.SLEEP_TRAP);
        this.rand = rand;
    }

    
    public void applyEffect(Player player) {
        int immobilizedTurns = rand.nextInt(5) + 1; // 1d5 turns
        player.setImmobile(immobilizedTurns);
    }


    @Override
    public String trigger(Player player) {
        this.reveal();
        applyEffect(player);
        return trapType.getMessage(); 
    }
    
}
