package com.models.dungeonofdoom.Items.Stick;

import com.models.Player;
import com.models.dungeonofdoom.Items.ItemEffect;
import com.models.dungeonofdoom.dungeonfloor.DungeonFloor;
import com.models.dungeonofdoom.monster.Monster;

public class Fire implements ItemEffect{

    @Override
    public void applyToPlayer(Player player, DungeonFloor df) {
        // do nothing
    }

    @Override
    public void applyToMonster(Monster monster) {
        // This method should delegate to the CombatManager or use similar hit chance logic
        throw new UnsupportedOperationException("Fire stick effect should be implemented in CombatManager");
    }

    @Override
    public String messageStringPlayer(Player player) {
        return "";
    }

    @Override
    public String messageStringMonster(Monster monster) {
        return "";
    }
    
}
