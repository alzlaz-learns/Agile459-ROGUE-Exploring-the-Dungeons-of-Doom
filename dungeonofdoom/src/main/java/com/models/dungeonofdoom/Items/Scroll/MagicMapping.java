package com.models.dungeonofdoom.Items.Scroll;

import com.models.Player;
import com.models.dungeonofdoom.Items.ItemEffect;
import com.models.dungeonofdoom.dungeonfloor.DungeonFloor;
import com.models.dungeonofdoom.monster.Monster;

public class MagicMapping implements ItemEffect {

    @Override
    public void applyToPlayer(Player player) {
        // This will be overridden to require DungeonFloor
    }

    @Override
    public void applyToPlayer(Player player, DungeonFloor floor) {
        floor.revealMap(); // Calls a method in DungeonFloor to reveal the layout
    }

    @Override
    public void applyToMonster(Monster monster) {
        throw new UnsupportedOperationException("Monsters cannot use Magic Mapping.");
    }

    @Override
    public String messageStringPlayer(Player player) {
        return "Oh, now this scroll has a map on it.";
    }

    @Override
    public String messageStringMonster(Monster monster) {
        return "";
    }


}
