package com.models.dungeonofdoom.Items.Potion;

import com.models.Player;
import com.models.dungeonofdoom.Items.ItemEffect;
import com.models.dungeonofdoom.monster.Monster;

public class GainStrength implements ItemEffect{

    @Override
    public void applyToPlayer(Player player) {
        int res = player.getStrength() + 1;
        
        if(res > player.getMaxStrength()){
            player.adjustMaxStrength();
            player.adjustStrength(1);
        }else{
            player.adjustStrength(1);
        }
    }

    @Override
    public void applyToMonster(Monster monster) {
        
    }

    @Override
    public String messageStringPlayer(Player player) {
        // TODO Auto-generated method stub
        return "You feel stronger. What bulging muscles!";
    }

    @Override
    public String messageStringMonster(Monster monster) {
        // never should be called
        return "";
    }

}
