package com.models.dungeonofdoom;

import java.util.Random;

import com.models.Player;
import com.models.dungeonofdoom.enums.TrapTypeEnum;

public class ArrowTrap extends AbstractTrap{

    public ArrowTrap(boolean hidden) {
        super(hidden, TrapTypeEnum.ARROW_TRAP);
        //TODO Auto-generated constructor stub
    }


    //trigger method is semi-temporary until player is fully implemented.
    @Override
    public String trigger(Player player) {
        this.reveal();

        Random rand = new Random();
        int hit = rand.nextInt(2); 

        if(hit == 1){ 
            int damage = rand.nextInt(6) + 1; // 1d6 damage
            //to be changed once implemented
            player.setCurrentHealth(player.getCurrentHealth() - damage);

            if (player.getCurrentHealth() <= 0) {
                return "An arrow killed you";
            }
            //to be updated latter to match the output of from design doc
            return "Oh no! An arrow shot you for " + damage + " damage!"; 
        }

        return "An arrow shoots past you!";
    }

}
