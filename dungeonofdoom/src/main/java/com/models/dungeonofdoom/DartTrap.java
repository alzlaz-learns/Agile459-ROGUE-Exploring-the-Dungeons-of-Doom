package com.models.dungeonofdoom;

import java.util.Random;

import com.models.Player;
import com.models.dungeonofdoom.enums.TrapTypeEnum;

public class DartTrap extends AbstractTrap {

    public DartTrap(boolean hidden) {
        super(hidden, TrapTypeEnum.DART_TRAP);
        //TODO Auto-generated constructor stub
    }

    @Override
    public void applyEffect(Player player) {
        Random rand = new Random();
        int hit = rand.nextInt(2);
        if(hit == 1){
            System.out.println("you got hit");
            int damage = rand.nextInt(4) + 1;
            // unsure how this will turn out when the method is implemented so hypothetical method
            //player.takeDamage(damage) 
            return;
        }
    }
    
}
