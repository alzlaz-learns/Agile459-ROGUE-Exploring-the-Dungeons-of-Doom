package com.models.dungeonofdoom.Items.Stick;

import com.models.Player;
import com.models.dungeonofdoom.Items.ItemEffect;
import com.models.dungeonofdoom.dungeonfloor.DungeonFloor;
import com.models.dungeonofdoom.monster.Monster;

public class MagicMissile implements ItemEffect {

    @Override
    public void applyToPlayer(Player player, DungeonFloor df) {
        // do nothing 
    }

    @Override
    public void applyToMonster(Monster monster) {
        // do nothing 
    }

    @Override
    public String messageStringPlayer(Player player) {
        // were only required to do this if they miss
        return "The missile vanishes in a puff of smoke.";
    }

    @Override
    public String messageStringMonster(Monster monster) {
        return "";
    }
    
}
