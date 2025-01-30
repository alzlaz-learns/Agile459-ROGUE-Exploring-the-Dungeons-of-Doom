package com.models.dungeonofdoom;

import java.util.Random;

import com.models.Player;
import com.models.dungeonofdoom.enums.TrapTypeEnum;

public class SleepTrap extends AbstractTrap{

    public SleepTrap(boolean hidden) {
        super(hidden, TrapTypeEnum.SLEEP_TRAP);
        //TODO Auto-generated constructor stub
    }

    
    public void applyEffect(Player player) {
        // TODO Auto-generated method stub
        Random rand = new Random();
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
