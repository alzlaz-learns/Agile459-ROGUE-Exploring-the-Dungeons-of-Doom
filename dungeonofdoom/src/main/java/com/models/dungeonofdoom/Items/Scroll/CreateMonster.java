package com.models.dungeonofdoom.Items.Scroll;

import com.models.Player;
import com.models.dungeonofdoom.Items.ItemEffect;
import com.models.dungeonofdoom.dungeonfloor.DungeonFloor;
import com.models.dungeonofdoom.monster.Monster;

public class CreateMonster implements ItemEffect{

    
    @Override
    public void applyToPlayer(Player player, DungeonFloor floor) {
        floor.spawnSingleMonster();
    }

    @Override
    public void applyToMonster(Monster monster) {
        throw new UnsupportedOperationException("Monsters cannot use the Create Monster scroll.");
    }

    @Override
    public String messageStringPlayer(Player player) {
        return "You hear a faint cry of anguish in the distance.";
    }

    @Override
    public String messageStringMonster(Monster monster) {
        return "";
    }

}
