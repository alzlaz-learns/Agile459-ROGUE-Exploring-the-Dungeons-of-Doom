package com.models.dungeonofdoom.Items.Potion;

import com.models.Player;
import com.models.dungeonofdoom.monster.Monster;

public class Poison implements PotionEffect{

    @Override
    public void applyToPlayer(Player player) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'applyToPlayer'");
    }

    @Override
    public void applyToMonster(Monster monster) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'applyToMonster'");
    }

    @Override
    public String messageStringPlayer(Player player) {
        
        // String res;
        // if(player.hasRingOfSustain()){
        //     res = "You feel very sick";
        // }else{
        //     res = "You feel momentarily sick";
        // }
        // return res;
    }

    @Override
    public String messageStringMonster(Monster monster) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'messageStringMonster'");
    }

}
