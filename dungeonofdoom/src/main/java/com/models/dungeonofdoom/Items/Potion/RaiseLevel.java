package com.models.dungeonofdoom.Items.Potion;

import com.models.Player;
import com.models.dungeonofdoom.monster.Monster;

public class RaiseLevel implements PotionEffect{

    @Override
    public void applyToPlayer(Player player) {
        player.increaseLvl();
    }

    @Override
    public void applyToMonster(Monster monster) {
        monster.increaseLvl();
        monster.setMaxHpt(monster.getMaxHpt() + 8);
        monster.setCurrentHpt(monster.getHpt() + 8);
    }

    @Override
    public String messageStringPlayer(Player player) {
       
        return "You suddenly feel much more skilful";
    }

    @Override
    public String messageStringMonster(Monster monster) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'messageStringMonster'");
    }

}
