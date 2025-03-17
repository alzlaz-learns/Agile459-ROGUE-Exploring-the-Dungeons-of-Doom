package com.models.dungeonofdoom.Items.Potion;

import com.models.Player;
import com.models.dungeonofdoom.Items.ItemEffect;
import com.models.dungeonofdoom.dungeonfloor.DungeonFloor;
import com.models.dungeonofdoom.monster.Monster;

public class RaiseLevel implements ItemEffect{

    @Override
    public void applyToPlayer(Player player, DungeonFloor df) {
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
        return "";
    }

}
