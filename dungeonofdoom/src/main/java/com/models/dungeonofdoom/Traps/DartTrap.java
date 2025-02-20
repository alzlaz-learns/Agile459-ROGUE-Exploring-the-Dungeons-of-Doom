package com.models.dungeonofdoom.Traps;

import java.util.Random;

import com.models.Player;
import com.models.dungeonofdoom.enums.TrapTypeEnum;

public class DartTrap extends AbstractTrap {

    Random rand;
    public DartTrap(boolean hidden, Random rand) {
        super(hidden, TrapTypeEnum.DART_TRAP);
        //TODO Auto-generated constructor stub
        this.rand = rand;
    }

    
    //trigger method is semi-temporary until player is fully implemented.
    @Override
    public String trigger(Player player) {
        this.reveal();

        int hit = rand.nextInt(2); 

        if(hit == 1){ 
            int damage = rand.nextInt(4) + 1; // 1d6 damage

            //to be changed once implemented
            int newHealth = Math.max(0, player.getCurrentHealth() - damage);
            player.setCurrentHealth(newHealth);

            if (player.getCurrentHealth() <= 0) {
                return "A poisoned dart killed you";
            }
            //to be updated latter to match the output of from design doc
            player.adjustStrength(-1);
            return "A dart just hit you in the shoulder " + damage + " damage!";
        }

        return "A dart whizzes by your ear and vanishes";
    }
    
}
