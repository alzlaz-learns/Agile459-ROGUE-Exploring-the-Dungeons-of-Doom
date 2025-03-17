package com.models.dungeonofdoom.Items.Stick;

import com.models.Player;
import com.models.dungeonofdoom.Items.ItemEffect;
import com.models.dungeonofdoom.dungeonfloor.DungeonFloor;
import com.models.dungeonofdoom.monster.Monster;

public class Cancellation implements ItemEffect {

    @Override
    public void applyToPlayer(Player player, DungeonFloor df) {
        // do nothing
    }

    @Override
    public void applyToMonster(Monster monster) {
        // just gonna call it good and not do disguising logic
        if (monster.isInvisible()) {
            monster.setInvisible(false);
        }
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
