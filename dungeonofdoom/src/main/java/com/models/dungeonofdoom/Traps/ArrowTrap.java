package com.models.dungeonofdoom.Traps;

import java.util.Random;

import com.models.Player;
import com.models.dungeonofdoom.enums.TrapTypeEnum;

public class ArrowTrap extends AbstractTrap{
    Random rand;
    public ArrowTrap(boolean hidden,Random rand) {
        super(hidden, TrapTypeEnum.ARROW_TRAP);
        //TODO Auto-generated constructor stub
        this.rand = rand;

    }


    //trigger method is semi-temporary until player is fully implemented.
    //ui isnt updating properly until walking away from the trap.
    @Override
    public String trigger(Player player) {
        this.reveal();
        int hit = rand.nextInt(2); 

        if(hit == 1){ 
            int damage = rand.nextInt(6) + 1; // 1d6 damage

            //to be changed once implemented
            int newHealth = Math.max(0, player.getCurrentHealth() - damage);
            player.setCurrentHealth(newHealth);

            if (player.getCurrentHealth() <= 0) {
                return "An arrow killed you";
            }
            //to be updated latter to match the output of from design doc
            return "Oh no! An arrow shot you for " + damage + " damage!"; 
        }

        return "An arrow shoots past you!";
    }

}
