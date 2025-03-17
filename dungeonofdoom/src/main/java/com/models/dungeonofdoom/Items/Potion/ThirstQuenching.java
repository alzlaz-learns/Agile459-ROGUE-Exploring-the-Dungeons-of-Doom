package com.models.dungeonofdoom.Items.Potion;

import com.models.Player;
import com.models.dungeonofdoom.Items.ItemEffect;
import com.models.dungeonofdoom.dungeonfloor.DungeonFloor;
import com.models.dungeonofdoom.monster.Monster;

public class ThirstQuenching implements ItemEffect{

    @Override
    public void applyToPlayer(Player player, DungeonFloor df) {
        
    }

    @Override
    public void applyToMonster(Monster monster) {
        
    }

    @Override
    public String messageStringPlayer(Player player) {
        return "This potion tastes extremely dull ";
    }

    @Override
    public String messageStringMonster(Monster monster) {
        return "";
    }

}
